angular.module("routingApp").controller("ClientListCtrl", [
    "$rootScope",
    "$scope",
    "$http",
    "APP_URL",
    "$location",
    "$routeParams",
    "$filter",
    "$window",
    function ($rootScope, $scope, $http, APP_URL, $location, $routeParams, $filter) {
        $scope.token = `Bearer ${localStorage.getItem("token")}`;
        const notyf = new Notyf({
            duration: 2500,
            position: {
                x: "right",
                y: "top",
            },
        });

        this.findOwners = () => {
            if (localStorage.getItem("token")) {
                if (localStorage.getItem("role") == "ROLE_RECEPCIONISTA") {
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
            }
            $window.location.href = "/#!/login";
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