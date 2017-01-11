angular.module('cwmd').component('workingArea', {
    templateUrl: 'app/js/user/working-area/workingArea.html',
    controller: function (DRDocumentSrv, RNDocumentSrv) {
        var $ctrl = this;

        $ctrl.rnDownloadLink = "http://localhost:8080/rn/RN_document_sample";

        $ctrl.$onInit = function () {
            DRDocumentSrv.getDocumentsByUser()
                .then(function (response) {
                    $ctrl.drDocuments = response;
                })
                .catch(function (response) {
                    console.log(response);
                });
            RNDocumentSrv.getDocumentsByUser()
                .then(function (response) {
                    $ctrl.rnDocuments = response;
                })
                .catch(function (response) {
                    console.log(response);
                });
        };
    }
});