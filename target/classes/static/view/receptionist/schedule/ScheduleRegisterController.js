angular.module("routingApp").controller("ScheduleRegisterCtrl", [
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
            this.findVisitReasons();
            this.findOwners();
        }

        (() => {
            "use strict";
            const forms = document.querySelectorAll(".needs-validation");
            Array.prototype.slice.call(forms).forEach((form) => {
                form.addEventListener(
                    "submit",
                    (event) => {
                        if(!form.checkValidity()) {
                            event.preventDefault();
                            event.stopPropagation();
                            notyf.error("Llena los campos necesarios");
                        } else {
                            this.save();
                        }form.classList.add("was-validated");
                    }, false)
            });
        })();

        this.findOwners = () => {
            return $http({
                method: "GET",
                url: `${APP_URL.url}/owner`,
                headers: {
                    "Content-Type": "application/json",
                    Accept: "application/json",
                    authorization: $scope.token,
                },
            }).then((res) => {
                $scope.owners = res.data;
            })
        }

        this.findVisitReasons = () => {
            return $http({
                method: "GET",
                url: `${APP_URL.url}/visitReason`,
                headers: {
                    "Content-Type": "application/json",
                    Accept: "application/json",
                    authorization: $scope.token,
                },
            }).then((res) => {
                $scope.reasonList = res.data;
            })
        }

        this.selectController = () => {
            this.findUsersForVisitReason();
            this.findConsultoriesByVisitReason();
        }

        this.findOwnersPets = () => {
            return $http({
                method: "GET",
                url: `${APP_URL.url}/patient/owner/${$scope.tmp.owner.id}`,
                headers: {
                    "Content-Type": "application/json",
                    Accept: "application/json",
                    authorization: $scope.token,
                },
            }).then((res) => {
                document.getElementById("pet").removeAttribute("disabled");
                $scope.pets = res.data;
            });
        }

        this.findUsersForVisitReason = () => {
            return $http({
                method: "GET",
                url: `${APP_URL.url}/visitReason/users/${$scope.tmp.visitReason.id}`,
                headers: {
                    "Content-Type": "application/json",
                    Accept: "application/json",
                    authorization: $scope.token,
                },
            }).then((res) => {
                document.getElementById("doctor").removeAttribute('disabled');
                $scope.responsibles = res.data;
            })
        }

        this.findConsultoriesByVisitReason = () => {
            return $http({
                method: "GET",
                url: `${APP_URL.url}/consultory/visit/${$scope.tmp.visitReason.id}`,
                headers: {
                    "Content-Type": "application/json",
                    Accept: "application/json",
                    authorization: $scope.token,
                },
            }).then((res) => {
                document.getElementById("consultory").removeAttribute('disabled');
                $scope.consultories = res.data;
            })
        }

        this.save = () => {
            return $http({
                method: "POST",
                url: `${APP_URL.url}/schedule/save`,
                headers: {
                    "Content-Type": "application/json",
                    Accept: "application/json",
                    authorization: $scope.token,
                },
                data: $scope.schedule,
            }).then((res) => {
                if (res.data) {
                    $window.location.href = `#!/receptionist/schedule/`;
                    notyf.success("Cita registrada");
                } else {
                    notyf.error("Ocurrió un error");
                }
            })
        }

        $(document).ready(function () {
            $("#visitReason").select2({
                placeholder: "SELECCIONAR UNA RAZÓN DE VISITA...",
                allowClear: false,
            });
            $("#consultory").select2({
                placeholder: "SELECCIONAR UN CONSULTORIO...",
                allowClear: false,
            });
            $("#doctor").select2({
                placeholder: "SELECCIONAR PERSONAL RESPONSABLE...",
                allowClear: false,
            });
            $("#owner").select2({
                placeholder: "SELECCIONAR UN DUEÑO...",
                allowClear: false,
            });
            $("#pet").select2({
                placeholder: "SELECCIONAR UNA MASCOTA...",
                allowClear: false,
            });
        })
    }
])