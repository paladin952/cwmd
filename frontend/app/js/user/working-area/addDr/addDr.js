angular.module('cwmd').component('addDr', {
    templateUrl: 'app/js/user/working-area/addDr/addDr.html',
    controller: function ($scope, $location, DRDocumentSrv) {
        var $ctrl = this;

        $ctrl.uploadFirstPart = function () {
            $('.btn').toggleClass('is-pending').prop('disabled', true);

            if ($scope.firstPart !== undefined) {
                DRDocumentSrv.uploadFirstPart($scope.firstPart)
                    .then(function (response) {
                        $('.btn').toggleClass('is-pending').prop('disabled', false);
                        $ctrl.documentId = response;
                    })
                    .catch(function (response) {
                    console.log(response);
                });
            }
        };

        $ctrl.uploadSecondPart = function () {
            $('.btn').toggleClass('is-pending').prop('disabled', true);

            if ($scope.secondPart !== undefined) {
                DRDocumentSrv.uploadSecondPart($scope.secondPart)
                    .then(function (response) {
                        $('.btn').toggleClass('is-pending').prop('disabled', false);
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