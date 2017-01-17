angular.module('cwmd')
    .config(function (RestangularProvider) {
        RestangularProvider.setBaseUrl('http://localhost:8080/');
        RestangularProvider.setDefaultHttpFields({withCredentials: true});
    });
