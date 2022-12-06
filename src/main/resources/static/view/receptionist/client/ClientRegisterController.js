angular.module("routingApp").controller("ClientRegisterCtrl", [
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
                    this.getStates();
                    this.getSuburbTypes();
                    this.getStreetTypes();
                    this.getAnimalTypes();
                }
            }
            $window.location.href = "/#!/login";
        }

        this.getCities = () => {
            return $http({
                method: "GET",
                url: `${APP_URL.url}/city/state/${$scope.tmp.state.id}`,
                headers: {
                    "Content-Type": "application/json",
                    Accept: "application/json",
                },
            }).then((res) => {
                document.getElementById("city").removeAttribute('disabled');
                $scope.citiesList = res.data;
            })
        }

        this.getStreetTypes = () => {
            return $http({
                method: "GET",
                url: `${APP_URL.url}/streetType`,
                headers: {
                    "Content-Type": "application/json",
                    Accept: "application/json",
                },
            }).then((res) => {
                $scope.streetList = res.data;
            })
        }

        this.getSuburbTypes = () => {
            return $http({
                method: "GET",
                url: `${APP_URL.url}/suburbType`,
                headers: {
                    "Content-Type": "application/json",
                    Accept: "application/json",
                },
            }).then((res) => {
                $scope.suburbList = res.data;
            })
        }

        this.getStates = () => {
            return $http({
                method: "GET",
                url: `${APP_URL.url}/state`,
                headers: {
                    "Content-Type": "application/json",
                    Accept: "application/json",
                },
            }).then((res) => {
                $scope.statesList = res.data;
            });
        }

        this.getAnimalTypes = () => {
            return $http({
                method: "GET",
                url: `${APP_URL.url}/animal`,
                headers: {
                    "Content-Type": "application/json",
                    Accept: "application/json",
                },
            }).then((res) => {
                $scope.animalTypeList = res.data
            })
        }

        this.save = () => {
            console.log($scope.dto);
            return $http({
                method: "POST",
                url: `${APP_URL.url}/owner/save`,
                headers: {
                    "Content-Type": "application/json",
                    Accept: "application/json",
                    Authorization: $scope.token,
                },
                data: $scope.dto,
            }).then((res) => {
                if (res.data) {
                    $window.location.href = "#!/receptionist/client";
                    notyf.success("Cliente registrado correctamente");
                }
            })
        }

        $(document).ready(function () {
            $('#state').select2({
                placeholder: "SELECCIONAR UN ESTADO...",
                allowClear: false
            });
            $('#city').select2({
                placeholder: "SELECCIONAR UN MUNICIPIO...",
                allowClear: false
            });
            $('#streetT').select2({
                placeholder: "SELECCIONAR UN TIPO DE VIALIDAD...",
                allowClear: false
            });
            $('#suburbT').select2({
                placeholder: "SELECCIONAR UN TIPO DE ASENTAMIENTO...",
                allowClear: false
            });
            $('#animalType').select2({
                placeholder: "SELECCIONAR UN TIPO DE ANIMAL...",
                allowClear: false
            });
            $('#gender').select2({
                placeholder: "SELECCIONAR SEXO...",
                allowClear: false
            });
        });
    }
]);