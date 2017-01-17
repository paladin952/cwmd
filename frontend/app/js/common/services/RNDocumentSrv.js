angular.module('cwmd').service('RNDocumentSrv', function (Restangular, FileSaver, Blob) {
    var service = this;

    service.getDocumentsByUser = function () {
        return Restangular.all('rn').getList();
    };

    service.getAllDocumentCount = function () {
        return Restangular.one('rn/count').get();
    };

    service.getAllPartOfAFlowDocumentCount = function () {
        return Restangular.one('rn/inFlow').get();
    };

    service.upload = function (file) {
        var fd = new FormData();
        fd.append('file', file);
        return Restangular.one('/rn/upload').withHttpConfig({transformRequest: angular.identity})
            .customPOST(fd, '', undefined, {'Content-Type': undefined})
    };
});