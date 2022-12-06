angular.module("routingApp").controller("ScheduleListCtrl", [
    "$rootScope",
    "$scope",
    "$http",
    "APP_URL",
    "$location",
    "$routeParams",
    "$filter",
    "$window",
    function ($rootScope, $scope, $http, APP_URL, $location, $routeParams, $filter, $window) {
        $scope.token = `Bearer ${localStorage.getItem("token")}`;
        const notyf = new Notyf({
            duration: 2500,
            position: {
                x: "right",
                y: "top",
            },
        });

        this.init = () => {
            if (localStorage.getItem("token")) {
                if (localStorage.getItem("role") == "ROLE_RECEPCIONISTA") {
                    this.findScheduleList();
                }
            }
            $window.location.href = "/#!/login";
        }

        this.findScheduleList = () => {
            return $http({
                method: "GET",
                url: `${APP_URL.url}/schedule`,
                headers: {
                    "Content-Type": "application/json",
                    Accept: "application/json",
                    authorization: $scope.token,
                },
            }).then((res) => {
                setTimeout(executeDataTable, 10);
                $scope.scheduleList = res.data;
            })
        }

        function executeDataTable() {
            $("#list").DataTable({
                language: {
                    url: "//cdn.datatables.net/plug-ins/1.10.16/i18n/Spanish.json",
                },
            });
        }

        this.findScheduleList2 = () => {
            return $http({
                method: "GET",
                url: `${APP_URL.url}/schedule`,
                headers: {
                    "Content-Type": "application/json",
                    Accept: "application/json",
                    authorization: $scope.token,
                },
            }).then((res) => {
                $scope.scheduleList = res.data;
            })
        }

        this.paymentAlert = (obj) => {
            Swal.fire({
                title: `¿Estás seguro de realizar el pago de $${obj.consultory.visitReason.cost}?`,
                text: `Se acreditarán ${obj.consultory.visitReason.cost * 0.05} puntos al monedero electrónico del cliente.`,
                icon: 'warning',
                showCancelButton: true,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                confirmButtonText: 'Pagar',
                cancelButtonText: 'Cancelar',
            }).then((result) => {
                if (result.isConfirmed) {
                    this.getOwnerBalance(obj);
                } else {
                    console.log("No pagado");
                }
            })
        }

        this.getOwnerBalance = (obj) => {
            return $http({
                method: "GET",
                url: `${APP_URL.url}/wallet/balance/${obj.patient.owner.id}`,
                headers: {
                    "Content-Type": "application/json",
                    Accept: "application/json",
                    authorization: $scope.token,
                },
            }).then((res) => {
                if (res.data > 0) {
                    let title;
                    let text;
                    if (res.data >= obj.consultory.visitReason.cost) {
                        title = "El cliente cuenta con puntos suficientes para cubrir el costo de la cita"
                        text = "¿Utilizar los puntos para cubrir el total a pagar?"
                    } else {
                        title = `El cliente cuenta con ${res.data} puntos en su monedero electrónico`;
                        text = "¿Utilizar los puntos para reducir la cantidad a pagar?";
                    }
                    Swal.fire({
                        title: title,
                        text: text,
                        icon: "info",
                        showCancelButton: true,
                        confirmButtonColor: '#3085d6',
                        cancelButtonColor: '#d33',
                        confirmButtonText: "Utilizar Puntos",
                        cancelButtonText: "No Utilizar Puntos",
                    }).then((result) => {
                        if (result.isConfirmed) {
                            this.walletPay(obj, 2);
                        } else {
                            this.changeScheduleStatus(obj, 2);
                        }
                    })
                } else {
                    this.changeScheduleStatus(obj, 2);
                }
            })
        }

        this.cancel = (obj) => {
            Swal.fire({
                title: "¿Estás seguro?",
                text: "Se cancelará esta cita",
                icon: "warning",
                showCancelButton: true,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                confirmButtonText: "Cancelar Cita",
                cancelButtonText: "No Cancelar Cita",
            }).then((result) => {
                if (result.isConfirmed) {
                    this.changeScheduleStatus(obj, 0);
                }
            })
        }

        this.changeScheduleStatus = (obj, status) => {
            obj.status = status;
            let msg = "";
            if (status == 2) {
                msg = "Pago efectuado";
            } else if (status == 0) {
                msg = "Cita cancelada";
            }
            return $http({
                method: "POST",
                url: `${APP_URL.url}/schedule/pay`,
                headers: {
                    "Content-Type": "application/json",
                    Accept: "application/json",
                    authorization: $scope.token,
                },
                data: obj,
            }).then((res) => {
                if (res.data) {
                    notyf.success(msg);
                    this.findScheduleList2();
                } else {
                    notyf.error("Ocurrió un error");
                }
            })
        }

        this.setModalObj = (obj) => {
            $scope.modalObj = obj;
            $scope.modalObj.visitDate = new Date(obj.visitDate);
        }

        this.update = () => {
            return $http({
                method: "POST",
                url: `${APP_URL.url}/schedule/save`,
                headers: {
                    "Content-Type": "application/json",
                    Accept: "application/json",
                    authorization: $scope.token,
                },
                data: $scope.modalObj,
            }).then((res) => {
                if (res.data) {
                    $("#exampleModal").modal("hide");
                    notyf.success("Cita reasignada");
                    $scope.modalObj = undefined;
                    this.findScheduleList2();
                } else {
                    notyf.error("Ocurrió un error")
                }
            })
        }

        this.walletPay = (obj, status) => {
            obj.status = status;
            return $http({
                method: "POST",
                url: `${APP_URL.url}/schedule/wallet/pay`,
                headers: {
                    "Content-Type": "application/json",
                    Accept: "application/json",
                    authorization: $scope.token,
                },
                data: obj,
            }).then((res) => {
                if (res.data) {
                    notyf.success("Pago efectuado");
                    this.findScheduleList2();
                } else {
                    notyf.error("Ocurrió un error");
                }
            })
        }
    }
])