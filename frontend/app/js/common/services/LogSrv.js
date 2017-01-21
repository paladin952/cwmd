angular.module('cwmd').service('LogSrv', function (Restangular) {
    var service = this;

    service.getAllLogs = function () {
        return Restangular.all('log/all').getList();
    };

    service.getAllLogsWithFilter = function (filter) {
        var path = "log/all/filter";

        var _params = {
            filter: filter
        };

        return Restangular.all(path).getList(_params);
    };

    service.getAllLogsWithTimestamp = function (from, to) {
        var path = "log/all/timestamps";
        var logTimeIntervalDTO = {
            from: from,
            to: to
        };
        return Restangular.all(path).getList(logTimeIntervalDTO);
    };

    service.getAllLogsWithAllFilters = function (filter, from, to) {
        var path = "log/all/all_filters";
        var parameters = {
            filter: filter,
            from: from,
            to: to
        };

        return Restangular.all(path).getList(parameters);
    };

    service.getAllDebugLogs = function () {
        var path = "log/debug";

        return Restangular.all(path).getList();
    };

    service.getAllDebugLogsWithFilter = function (filter) {
        var path = "log/debug/filter";

        var _params = {
            filter: filter
        };

        return Restangular.all(path).getList(_params);
    };

    service.getAllDebugLogsWithTimestamp = function (from, to) {
        var path = "log/debug/timestamps";
        var logTimeIntervalDTO = {
            from: from,
            to: to
        };

        return Restangular.all(path).getList(logTimeIntervalDTO);
    };

    service.getAllDebugLogsWithAllFilters = function (filter, from, to) {
        var path = "log/debug/all_filters";
        var parameters = {
            filter: filter,
            from: from,
            to: to
        };

        return Restangular.all(path).getList(parameters);
    };

    service.getAllInfoLogs = function () {
        var path = "log/info";

        return Restangular.all(path).getList();
    };

    service.getAllInfoLogsWithFilter = function (filter) {
        var path = "log/info/filter";

        var _params = {
            filter: filter
        };

        return Restangular.all(path).getList(_params);
    };

    service.getAllInfoLogsWithTimestamp = function (from, to) {
        var path = "log/info/timestamps";
        var logTimeIntervalDTO = {
            from: from,
            to: to
        };

        return Restangular.all(path).getList(logTimeIntervalDTO);
    };

    service.getAllInfoLogsWithAllFilters = function (filter, from, to) {
        var path = "log/info/all_filters";
        var interval = {};
        var parameters = {
            filter: filter,
            from: from,
            to: to
        };

        return Restangular.all(path).getList(parameters);
    };

    service.getAllWarnLogs = function () {
        var path = "log/warn";

        return Restangular.all(path).getList();
    };

    service.getAllWarnLogsWithFilter = function (filter) {
        var path = "log/warn/filter";

        var _params = {
            filter: filter
        };

        return Restangular.all(path).getList(_params);
    };

    service.getAllWarnLogsWithTimestamp = function (from, to) {
        var path = "log/warn/timestamps";
        var logTimeIntervalDTO = {
            from: from,
            to: to
        };

        return Restangular.all(path).getList(logTimeIntervalDTO);
    };

    service.getAllWarnLogsWithAllFilters = function (filter, from, to) {
        var path = "log/warn/all_filters";
        var parameters = {
            filter: filter,
            from: from,
            to: to
        };

        return Restangular.all(path).getList(parameters);
    };

    service.getAllErrorLogs = function () {
        var path = "log/error";

        return Restangular.all(path).getList();
    };

    service.getAllErrorLogsWithFilter = function (filter) {
        var path = "log/error/filter";

        var _params = {
            filter: filter
        };

        return Restangular.all(path).getList(_params);
    };

    service.getAllErrorLogsWithTimestamp = function (from, to) {
        var path = "log/error/timestamps";
        var logTimeIntervalDTO = {
            from: from,
            to: to
        };

        return Restangular.all(path).getList(logTimeIntervalDTO);
    };

    service.getAllErrorLogsWithAllFilters = function (filter, from, to) {
        var path = "log/error/all_filters";
        var parameters = {
            filter: filter,
            from: from,
            to: to
        };

        return Restangular.all(path).getList(parameters);
    };

});