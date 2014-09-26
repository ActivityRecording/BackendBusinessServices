'use strict';

var services = angular.module('services', ['ngResource']);

services.factory('Patient', function ($resource) {
    return $resource('rest/patient/:id', {}, {
        'update': {method: 'PUT'}
    });
});

