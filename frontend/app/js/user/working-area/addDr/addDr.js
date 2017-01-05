angular.module('cwmd').component('addDr', {
    templateUrl: 'app/js/user/working-area/addDr/addDr.html',
    controller: function ($scope, $location, DRDocumentSrv) {
        var $ctrl = this;

        $ctrl.uploadFirstPart = function () {
            if ($scope.firstPart !== undefined) {
                DRDocumentSrv.uploadFirstPart($scope.firstPart)
                    .then(function (response) {
                        $ctrl.documentId = response;
                    })
                    .catch(function (response) {
                    console.log(response);
                });
            }
        };

        $ctrl.uploadSecondPart = function () {
            if ($scope.secondPart !== undefined) {
                DRDocumentSrv.uploadSecondPart($scope.secondPart)
                    .then(function (response) {
                        console.log(response);
                        $location.path("/workingArea");
                    })
                    .catch(function (response) {
                        console.log(response);
                    });
            }
        };
    }
});