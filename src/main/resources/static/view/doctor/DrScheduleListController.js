angular.module("routingApp").controller("DrScheduleListCtrl", [
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
                if (localStorage.getItem("role") == "ROLE_DOCTOR" || localStorage.getItem("role") == "ROLE_ESTILISTA") {
                    this.findScheduleList();
                }
            }
            $window.location.href = "/#!/login";
        }

        this.findScheduleList = () => {
            return $http({
                method: "GET",
                url: `${APP_URL.url}/schedule/self`,
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

        this.findScheduleList2 = () => {
            return $http({
                method: "GET",
                url: `${APP_URL.url}/schedule/self`,
                headers: {
                    "Content-Type": "application/json",
                    Accept: "application/json",
                    authorization: $scope.token,
                },
            }).then((res) => {
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

        this.scheduleDone = (obj) => {
            Swal.fire({
                title: "¿Estás seguro?",
                text: "Se marcará esta cita como completado",
                icon: "info",
                showCancelButton: true,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                confirmButtonText: "Completar",
                cancelButtonText: "Cancelar",
            }).then((res) => {
                if (res.isConfirmed) {
                    obj.status = 3;
                    return $http({
                        method: "POST",
                        url: `${APP_URL.url}/schedule/save`,
                        headers: {
                            "Content-Type": "application/json",
                            Accept: "application/json",
                            authorization: $scope.token,
                        },
                        data: obj,
                    }).then((response) => {
                        if (response.data) {
                            notyf.success("Cita completada");
                            this.findScheduleList2();
                        } else {
                            notyf.error("Ocurrió un error");
                        }
                    })
                }
            })
        }
    }
])