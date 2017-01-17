angular.module('cwmd').component('downloadDr', {
    templateUrl: 'app/js/user/working-area/downloadDr/downloadDr.html',
    controller: function (DRDocumentSrv) {
        var $ctrl = this;

        $ctrl.firstPart = "http://localhost:8080/dr/First_part_sample";
        $ctrl.secondPart = "http://localhost:8080/dr/Second_part_sample";

        $ctrl.downloadFirst = function () {
            DRDocumentSrv.getFirstPart();
        };

        $ctrl.downloadSecond = function () {
            DRDocumentSrv.getSecondPart();
        };
    }
});