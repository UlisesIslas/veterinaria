angular.module("routingApp").controller("PetScheduleRegisterCtrl", [
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

        $scope.pet = {};

        this.init = () => {
            if (localStorage.getItem("token")) {
                if (localStorage.getItem("role") == "ROLE_RECEPCIONISTA") {
                    this.findPet();
                    this.findVisitReasons();
                }
            }
            $window.location.href = "/#!/login";
        }

        this.findPet = () => {
            if ($routeParams.id) {
                return $http({
                    method: "GET",
                    url: `${APP_URL.url}/patient/${$routeParams.id}`,
                    headers: {
                        "Content-Type": "application/json",
                        Accept: "application/json",
                        authorization: $scope.token,
                    },
                }).then((res) => {
                    $scope.pet = res.data;
                })
            }
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
                document.getElementById("responsible").removeAttribute('disabled');
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
            $scope.schedule.patient = $scope.pet;
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
                    $window.location.href = `#!/receptionist/client/pets/schedule/${$scope.pet.id}`;
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
            })
            $("#responsible").select2({
                placeholder: "SELECCIONAR PERSONAL RESPONSABLE...",
                allowClear: false,
            })
        })

    }
])