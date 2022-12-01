angular.module("routingApp").controller("PetScheduleListCtrl", [
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

        $scope.pet = {};

        this.init = () => {
            this.findPet();
            this.findScheduleList();
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

        this.findScheduleList = () => {
            if ($routeParams.id) {
                return $http({
                    method: "GET",
                    url: `${APP_URL.url}/schedule/pet/${$routeParams.id}`,
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
        }

        function executeDataTable() {
            $("#list").DataTable({
                language: {
                    url: "//cdn.datatables.net/plug-ins/1.10.16/i18n/Spanish.json",
                },
            });
        }
    }
])