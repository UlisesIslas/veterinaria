angular.module("routingApp").controller("ClientListCtrl", [
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

        this.findDetails = (id) => {
            return $http({
                method: "GET",
                url: `${APP_URL.url}/owner/details/${id}`,
                headers: {
                    "Content-Type": "application/json",
                    Accept: "application/json",
                    authorization: $scope.token,
                },
            }).then((res) => {
                $scope.modalObj = res.data;
            })
        }

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
                $scope.ownersList = res.data;
                setTimeout(executeDataTable, 10);
            })
        }

        function executeDataTable() {
            $("#clients").DataTable({
                language: {
                    url: "//cdn.datatables.net/plug-ins/1.10.16/i18n/Spanish.json",
                },
            });
        }
    }
])