'use strict';

var services = angular.module('services', ['ngResource']);

services.factory('Patient', function ($resource) {
    return $resource('rest/patients/:id', {}, {
        'update': {method: 'PUT'}
    });
});

