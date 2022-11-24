angular
    .module("routingApp", ["ngRoute", "ngSanitize"])
    .config([
        "$routeProvider",
        function ($routeProvider) {
            $routeProvider
                .when("/", {
                    templateUrl: "/landing.html"
                })
                .when("/login", {
                    templateUrl: "/login.html",
                    controller: "LoginCtrl",
                    controllerAs: "ctrlLogin",
                })
                .when("/admin/user/register", {
                    templateUrl: "/view/admin/users/user_register.html",
                    controller: "UserRegisterCtrl",
                    controllerAs: "ctrlUserRegister",
                })
        }
    ]).constant('APP_URL', {
        url: "http://localhost:8080"
    })
    .value("SESSION", {
        token: localStorage.getItem("token"),
    }).run(function ($rootScope, $location) {
        $rootScope.$on(
            "$routeChangeError",
            function (event, current, previous, rejection) {
                if (rejection === "Not Authenticated") {
                    $location.path("/login");
                } else if (rejection === "Session") {
                    $location.path("/");
                }
            }
        );
    });