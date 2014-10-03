'use strict';

var patientmanagementapp = angular.module('patientmanagementapp', ['controllers', 'services','directives'],

	function($routeProvider, $locationProvider) {
	
                $routeProvider.when('/admin.patients', {
			templateUrl : 'admin.patients.html',
			controller : PatientController
		});
        
		$routeProvider.when('/view.testreports', {
			templateUrl : 'view.testreports.html',
			controller : TestReportController
		});
	
		$routeProvider.when('/admin.authors', {
			templateUrl : 'admin.authors.html',
			controller : AuthorController
		});
		
		$routeProvider.when('/admin.cameras', {
			templateUrl : 'admin.cameras.html',
			controller : CameraController
		});
		
		$routeProvider.when('/admin.testreports', {
			templateUrl : 'admin.testreports.html',
			controller : TestReportController
		});
		
		$routeProvider.when('/admin.comments', {
			templateUrl : 'admin.comments.html',
			controller : CommentController
		});
	}
);

