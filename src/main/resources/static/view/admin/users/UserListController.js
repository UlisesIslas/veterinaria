angular.module("routingApp").controller("UserListCtrl", [
    "$rootScope",
    "$scope",
    "$http",
    "APP_URL",
    "$location",
    "$routeParams",
    "$filter",
    function ($rootScope, $scope, $http, APP_URL, $location, $routeParams, $filter) {
        $scope.token = "Bearer " + localStorage.getItem("token");

        const notyf = new Notyf({
            duration: 2500,
            position: {
                x: "right",
                y: "top",
            },
        });

        this.getUsers = () => {
            return $http({
                method: "GET",
                url: `${APP_URL.url}/users`,
                headers: {
                    "Content-Type": "application/json",
                            Accept: "application/json",
                            authorization: $scope.token,
                },
            }).then((res) => {
                if (res.data.code == 200) {
                    $scope.usersList = res.data.object;
                    setTimeout(executeDataTable, 10);
                } else {
                    notyf.error(res.data.message);
                }
            });
        }

        function executeDataTable() {
            $("#users").DataTable({
                language: {
                    url: "//cdn.datatables.net/plug-ins/1.10.16/i18n/Spanish.json",
                },
            });
        }
    }
]);