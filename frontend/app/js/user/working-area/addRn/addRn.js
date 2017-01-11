angular.module('cwmd').component('addRn', {
    templateUrl: 'app/js/user/working-area/addRn/addRn.html',
    controller: function ($scope, $location, RNDocumentSrv) {
        var $ctrl = this;

        $ctrl.upload = function () {

            $('.btn').toggleClass('is-pending').prop('disabled', true);

            if ($scope.document !== undefined) {
                RNDocumentSrv.upload($scope.document)
                    .then(function (response) {
                        $('.btn').toggleClass('is-pending').prop('disabled', false);
                        $location.path("/workingArea");
                    })
                    .catch(function (response) {
                        console.log(response);
                    });
            }
        };
    }
});