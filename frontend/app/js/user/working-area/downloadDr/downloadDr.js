angular.module('cwmd').component('downloadDr', {
    templateUrl: 'app/js/working-area/downloadDr/downloadDr.html',
    controller: function (DRDocumentSrv) {
        var $ctrl = this;

        $ctrl.downloadFirst = function () {
            DRDocumentSrv.getFirstPart();
        };

        $ctrl.downloadSecond = function () {
            DRDocumentSrv.getSecondPart();
        };
    }
});