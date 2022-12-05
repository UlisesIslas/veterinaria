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
            
        }
    }
])