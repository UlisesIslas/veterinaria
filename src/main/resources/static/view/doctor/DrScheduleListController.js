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
            this.findScheduleList();
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
    }
])