angular.module("routingApp").controller("PetListCtrl", [
    "$rootScope",
    "$scope",
    "$http",
    "APP_URL",
    "$location",
    "$routeParams",
    "$filter",
    "$window",
    function ($rootScope, $scope, $http, APP_URL, $location, $routeParams, $filter) {
        $scope.token =  `Bearer ${localStorage.getItem("token")}`;
        const notyf = new Notyf({
            duration: 2500,
            position: {
                x: "right",
                y: "top",
            },
        });

        $scope.owner = {};

        this.init = () => {
            this.findOwner();
            this.findOwnerPets();
            this.getAnimalTypes();
        }

        this.findOwner = () => {
            if ($routeParams.id) {
                return $http({
                    method: "GET",
                    url: `${APP_URL.url}/owner/${$routeParams.id}`,
                    headers: {
                        "Content-Type": "application/json",
                        Accept: "application/json",
                        authorization: $scope.token,
                    },
                }).then((res) => {
                    $scope.owner = res.data;
                })
            }
        }

        function executeDataTable() {
            $("#clients").DataTable({
                language: {
                    url: "//cdn.datatables.net/plug-ins/1.10.16/i18n/Spanish.json",
                },
            });
        }

        this.findOwnerPets = () => {
            if ($routeParams.id) {
                return $http({
                    method: "GET",
                    url: `${APP_URL.url}/patient/owner/${$routeParams.id}`,
                    headers: {
                        "Content-Type": "application/json",
                        Accept: "application/json",
                        authorization: $scope.token,
                    },
                }).then((res) => {
                    setTimeout(executeDataTable, 10);
                    $scope.petsList = res.data;
                });
            }
        }

        this.findOwnerPets2 = () => {
            if ($routeParams.id) {
                return $http({
                    method: "GET",
                    url: `${APP_URL.url}/patient/owner/${$routeParams.id}`,
                    headers: {
                        "Content-Type": "application/json",
                        Accept: "application/json",
                        authorization: $scope.token,
                    },
                }).then((res) => {
                    //setTimeout(executeDataTable, 10);
                    $scope.petsList = res.data;
                });
            }
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
            $scope.pet.owner = $scope.owner;
            return $http({
                method: "POST",
                url: `${APP_URL.url}/patient/save`,
                headers: {
                    "Content-Type": "application/json",
                    Accept: "application/json",
                },
                data: $scope.pet,
            }).then((res) => {
                if (res.data) {
                    $scope.pet = {};
                    notyf.success("Mascota Registrada");
                    this.findOwnerPets2();
                    $("#exampleModal").modal("hide");
                }
            })
        }

        $(document).ready(function () {
            $('#animalType').select2({
                placeholder: "SELECCIONAR UN TIPO DE ANIMAL...",
                allowClear: false,
                dropdownParent: $(".modal"),
            });
            $('#gender').select2({
                placeholder: "SELECCIONAR UN TIPO DE ANIMAL...",
                allowClear: false,
                dropdownParent: $(".modal"),
            });
        })
    }
])