(window["webpackJsonp"] = window["webpackJsonp"] || []).push([["dashboard-dashboard-module"],{

/***/ "./node_modules/raw-loader/index.js!./src/app/dashboard/dashboard.component.html":
/*!******************************************************************************!*\
  !*** ./node_modules/raw-loader!./src/app/dashboard/dashboard.component.html ***!
  \******************************************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "<ngx-spinner></ngx-spinner>\n<section id=\"minimal-statistics-bg\">\n  <!-- <div class=\"row text-left\">\n    <div class=\"col-12 mt-3 mb-1\">\n      <div class=\"content-header\"> HOI API Monitoring Tool</div>\n    </div>\n  </div>\n  <div class=\"form-actions c\">\n    <div class=\"card-body\">\n      <div class=\"places-buttons\">\n        <div class=\"row\">\n          <div *ngFor=\"let api of apis\">\n            <button class=\"btn btn-primary\" style=\"width: 280px; height: 130px; margin-right: 50px; margin-bottom:50px;\"\n              (click)=\"viewStatus(api)\">\n              <div>\n                <i [className]=\"api.cssClass\"></i>\n              </div>\n              <span style=\"color: white; font-size : 12px \">{{api.appName}}&nbsp;&nbsp;-&nbsp;&nbsp;{{api.percent}}%\n                <br><br>Avg Latency : {{api.avgResponseTime}}ms\n                <br>&nbsp;Avg DNS Lookup Time : {{api.avgDNSLookupTime}}ms\n                <br>&nbsp;&nbsp;Avg TCPIP Connect Time : {{api.avgTCPIPConnectTime}}ms\n              </span>\n            </button>\n          </div>\n        </div>\n      </div>\n    </div>\n  </div> -->\n<div  class=\"row\">\n  <div class=\"col-xl-4 col-lg-6 col-md-6 col-12\">\n\t\t<div class=\"card gradient-pomegranate\">\n\t\t\t<div class=\"card-content\">\n\t\t\t\t<div class=\"card-body pt-2 pb-0\">\n\t\t\t\t\t<div class=\"media\">\n\t\t\t\t\t\t<div class=\"media-body white text-center\">\n\t\t\t\t\t\t\t\t<button type=\"button\" class=\"btn btn-raised \" style=\"color:white\"\n\t\t\t\t\t\t\t\trouterLink=\"/reports/assessmentreport\">\n\t\t\t\t\t\t\t\tStore 1</button>\n\t\t\t\t\t\t</div>\n          </div>\n\t\t\t\t</div>\n\t\t\t</div>\n\t\t</div>\n\t</div>\n  <div class=\"col-xl-4 col-lg-6 col-md-6 col-12\">\n\t\t<div class=\"card gradient-blackberry\">\n\t\t\t<div class=\"card-content\">\n\t\t\t\t<div class=\"card-body pt-2 pb-0\">\n\t\t\t\t\t<div class=\"media\">\n\t\t\t\t\t\t<div class=\"media-body white text-center\">\n\t\t\t\t\t\t\t\t<button type=\"button\" class=\"btn btn-raised \" style=\"color:white\"\n\t\t\t\t\t\t\t\trouterLink=\"/reports/assessmentreport\">\n\t\t\t\t\t\t\t\tStore2</button>\n\t\t\t\t\t\t</div>\n\t\t\t\t\t\t</div>\n\t\t\t\t</div>\n\t\t\t</div>\n\t\t</div>\n\t</div>\n  </div>\n</section>"

/***/ }),

/***/ "./node_modules/raw-loader/index.js!./src/app/dashboard/view-status/view-status.component.html":
/*!********************************************************************************************!*\
  !*** ./node_modules/raw-loader!./src/app/dashboard/view-status/view-status.component.html ***!
  \********************************************************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "<ngx-spinner ></ngx-spinner>\n<section id=\"simple\">\n  <div class=\"row text-left\">\n    <div class=\"col-sm-12\">\n      <div class=\"card\">\n          <div class=\"card-header pb-2\">\n              <h4 class=\"card-title\" id=\"basic-layout-colored-form-control\">URL Details: {{appURL}}</h4>\n              <button type=\"button\"class=\"btn btn-raised btn-warning\"                      \n              routerLink=\"/dashboard\">\n              <i class=\"ft-x\"></i> Back\n          </button>\n          </div>\n        <div class=\"card-body\">\n          <div class=\"col-sm-12\">\n\n            <table class=\"table table-responsive-md \">\n              <thead>\n                <tr>\n                  <th>Id</th>\n                  <th>Status</th>\n                  <th>Latency</th>\n                  <th>DNS LookUp Time</th>\n                  <th>TCP IP ConnectTime</th>\n                  <th>Date & Time</th>\n                </tr> \n              </thead>\n              <tbody *ngFor=\"let status of statuses; index as i\">\n                <tr>\n                  <td>{{i+1}}</td>\n                  <td>{{status.status}}</td>\n                  <td>{{status.responseTime}}ms</td>\n                  <td>{{status.dnsLookupTime}}ms</td>\n                  <td>{{status.tcpIPConnectTime}}ms</td>\n                  <td>{{status.createdAt|date:'dd-MM-yyyy HH:mm:ss'}}</td>\n                 \n                </tr>\n              </tbody>\n            </table>\n          </div>\n        </div>\n      </div>\n    </div>\n  </div>\n</section>"

/***/ }),

/***/ "./src/app/dashboard/dashboard-routing.module.ts":
/*!*******************************************************!*\
  !*** ./src/app/dashboard/dashboard-routing.module.ts ***!
  \*******************************************************/
/*! exports provided: DashboardRoutingModule */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "DashboardRoutingModule", function() { return DashboardRoutingModule; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/router */ "./node_modules/@angular/router/fesm5/router.js");
/* harmony import */ var _dashboard_component__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ./dashboard.component */ "./src/app/dashboard/dashboard.component.ts");
/* harmony import */ var _view_status_view_status_component__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! ./view-status/view-status.component */ "./src/app/dashboard/view-status/view-status.component.ts");





var routes = [
    {
        path: '',
        component: _dashboard_component__WEBPACK_IMPORTED_MODULE_3__["DashboardComponent"],
        data: {
            title: 'Dashboard'
        },
    },
    {
        path: 'view-status',
        component: _view_status_view_status_component__WEBPACK_IMPORTED_MODULE_4__["ViewStatusComponent"],
        data: {
            title: 'view-status'
        },
    },
];
var DashboardRoutingModule = /** @class */ (function () {
    function DashboardRoutingModule() {
    }
    DashboardRoutingModule = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["NgModule"])({
            imports: [_angular_router__WEBPACK_IMPORTED_MODULE_2__["RouterModule"].forChild(routes)],
            exports: [_angular_router__WEBPACK_IMPORTED_MODULE_2__["RouterModule"]]
        })
    ], DashboardRoutingModule);
    return DashboardRoutingModule;
}());



/***/ }),

/***/ "./src/app/dashboard/dashboard.component.scss":
/*!****************************************************!*\
  !*** ./src/app/dashboard/dashboard.component.scss ***!
  \****************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "/* You can add global styles to this file, and also import other style files */\nbody {\n  background-color: #f1f0f7;\n}\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbInNyYy9hcHAvZGFzaGJvYXJkL0Y6XFxPcGVuU3BhY2UgUHJvamVjdHNcXFNtYXJ0IFNhZmUgUHJvamVjdFxcU21hcnRTYWZlIFdlYlxcOS0zLTIwMjJcXHNtYXJ0c2FmZXdlYi9zcmNcXGFwcFxcZGFzaGJvYXJkXFxkYXNoYm9hcmQuY29tcG9uZW50LnNjc3MiLCJzcmMvYXBwL2Rhc2hib2FyZC9kYXNoYm9hcmQuY29tcG9uZW50LnNjc3MiXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6IkFBQUEsOEVBQUE7QUFDQTtFQUNJLHlCQUFBO0FDQ0oiLCJmaWxlIjoic3JjL2FwcC9kYXNoYm9hcmQvZGFzaGJvYXJkLmNvbXBvbmVudC5zY3NzIiwic291cmNlc0NvbnRlbnQiOlsiLyogWW91IGNhbiBhZGQgZ2xvYmFsIHN0eWxlcyB0byB0aGlzIGZpbGUsIGFuZCBhbHNvIGltcG9ydCBvdGhlciBzdHlsZSBmaWxlcyAqL1xyXG5ib2R5IHtcclxuICAgIGJhY2tncm91bmQtY29sb3I6IHJnYigyNDEsIDI0MCwgMjQ3KTtcclxuICB9IiwiLyogWW91IGNhbiBhZGQgZ2xvYmFsIHN0eWxlcyB0byB0aGlzIGZpbGUsIGFuZCBhbHNvIGltcG9ydCBvdGhlciBzdHlsZSBmaWxlcyAqL1xuYm9keSB7XG4gIGJhY2tncm91bmQtY29sb3I6ICNmMWYwZjc7XG59Il19 */"

/***/ }),

/***/ "./src/app/dashboard/dashboard.component.ts":
/*!**************************************************!*\
  !*** ./src/app/dashboard/dashboard.component.ts ***!
  \**************************************************/
/*! exports provided: DashboardComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "DashboardComponent", function() { return DashboardComponent; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var app_model_api__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! app/model/api */ "./src/app/model/api.ts");
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! @angular/router */ "./node_modules/@angular/router/fesm5/router.js");
/* harmony import */ var _angular_common_http__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! @angular/common/http */ "./node_modules/@angular/common/fesm5/http.js");
/* harmony import */ var app_model_status__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! app/model/status */ "./src/app/model/status.ts");
/* harmony import */ var ngx_spinner__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! ngx-spinner */ "./node_modules/ngx-spinner/fesm5/ngx-spinner.js");







var DashboardComponent = /** @class */ (function () {
    function DashboardComponent(http, router, spinner) {
        this.http = http;
        this.router = router;
        this.spinner = spinner;
        this.httpOptions = {
            headers: new _angular_common_http__WEBPACK_IMPORTED_MODULE_4__["HttpHeaders"]({
                'Access-Control-Allow-Origin': '*',
                'Access-Control-Allow-Methods': 'GET, POST, PATCH, PUT, DELETE, OPTIONS',
                'Access-Control-Allow-Headers': 'Origin, Content-Type, X-Auth-Token',
                'Authorization': 'Basic ' + btoa('dashboard:$dashboardPWD$')
            })
        };
        this.api = new app_model_api__WEBPACK_IMPORTED_MODULE_2__["Application"]();
        this.status = new app_model_status__WEBPACK_IMPORTED_MODULE_5__["Status"]();
    }
    DashboardComponent.prototype.ngOnInit = function () {
        // this.spinner.show();
        // this.getAllEndPointsList();
    };
    DashboardComponent.ctorParameters = function () { return [
        { type: _angular_common_http__WEBPACK_IMPORTED_MODULE_4__["HttpClient"] },
        { type: _angular_router__WEBPACK_IMPORTED_MODULE_3__["Router"] },
        { type: ngx_spinner__WEBPACK_IMPORTED_MODULE_6__["NgxSpinnerService"] }
    ]; };
    DashboardComponent = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Component"])({
            selector: 'app-dashboard',
            template: __webpack_require__(/*! raw-loader!./dashboard.component.html */ "./node_modules/raw-loader/index.js!./src/app/dashboard/dashboard.component.html"),
            styles: [__webpack_require__(/*! ./dashboard.component.scss */ "./src/app/dashboard/dashboard.component.scss")]
        }),
        tslib__WEBPACK_IMPORTED_MODULE_0__["__metadata"]("design:paramtypes", [_angular_common_http__WEBPACK_IMPORTED_MODULE_4__["HttpClient"], _angular_router__WEBPACK_IMPORTED_MODULE_3__["Router"], ngx_spinner__WEBPACK_IMPORTED_MODULE_6__["NgxSpinnerService"]])
    ], DashboardComponent);
    return DashboardComponent;
}());



/***/ }),

/***/ "./src/app/dashboard/dashboard.module.ts":
/*!***********************************************!*\
  !*** ./src/app/dashboard/dashboard.module.ts ***!
  \***********************************************/
/*! exports provided: DashboardModule */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "DashboardModule", function() { return DashboardModule; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_common__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/common */ "./node_modules/@angular/common/fesm5/common.js");
/* harmony import */ var _dashboard_routing_module__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ./dashboard-routing.module */ "./src/app/dashboard/dashboard-routing.module.ts");
/* harmony import */ var _dashboard_component__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! ./dashboard.component */ "./src/app/dashboard/dashboard.component.ts");
/* harmony import */ var _view_status_view_status_component__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! ./view-status/view-status.component */ "./src/app/dashboard/view-status/view-status.component.ts");
/* harmony import */ var ngx_spinner__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! ngx-spinner */ "./node_modules/ngx-spinner/fesm5/ngx-spinner.js");







var DashboardModule = /** @class */ (function () {
    function DashboardModule() {
    }
    DashboardModule = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["NgModule"])({
            declarations: [_dashboard_component__WEBPACK_IMPORTED_MODULE_4__["DashboardComponent"], _view_status_view_status_component__WEBPACK_IMPORTED_MODULE_5__["ViewStatusComponent"]],
            imports: [
                _angular_common__WEBPACK_IMPORTED_MODULE_2__["CommonModule"],
                _dashboard_routing_module__WEBPACK_IMPORTED_MODULE_3__["DashboardRoutingModule"],
                ngx_spinner__WEBPACK_IMPORTED_MODULE_6__["NgxSpinnerModule"]
            ]
        })
    ], DashboardModule);
    return DashboardModule;
}());



/***/ }),

/***/ "./src/app/dashboard/view-status/view-status.component.scss":
/*!******************************************************************!*\
  !*** ./src/app/dashboard/view-status/view-status.component.scss ***!
  \******************************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6IiIsImZpbGUiOiJzcmMvYXBwL2Rhc2hib2FyZC92aWV3LXN0YXR1cy92aWV3LXN0YXR1cy5jb21wb25lbnQuc2NzcyJ9 */"

/***/ }),

/***/ "./src/app/dashboard/view-status/view-status.component.ts":
/*!****************************************************************!*\
  !*** ./src/app/dashboard/view-status/view-status.component.ts ***!
  \****************************************************************/
/*! exports provided: ViewStatusComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "ViewStatusComponent", function() { return ViewStatusComponent; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var environments_environment__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! environments/environment */ "./src/environments/environment.ts");
/* harmony import */ var app_model_status__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! app/model/status */ "./src/app/model/status.ts");
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! @angular/router */ "./node_modules/@angular/router/fesm5/router.js");
/* harmony import */ var _angular_common_http__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! @angular/common/http */ "./node_modules/@angular/common/fesm5/http.js");
/* harmony import */ var ngx_spinner__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! ngx-spinner */ "./node_modules/ngx-spinner/fesm5/ngx-spinner.js");







var ViewStatusComponent = /** @class */ (function () {
    function ViewStatusComponent(http, router, spinner) {
        this.http = http;
        this.router = router;
        this.spinner = spinner;
        this.httpOptions = {
            headers: new _angular_common_http__WEBPACK_IMPORTED_MODULE_5__["HttpHeaders"]({
                'Access-Control-Allow-Origin': '*',
                'Access-Control-Allow-Methods': 'GET, POST, PATCH, PUT, DELETE, OPTIONS',
                'Access-Control-Allow-Headers': 'Origin, Content-Type, X-Auth-Token',
                'Authorization': 'Basic ' + btoa('dashboard:$dashboardPWD$')
            })
        };
        this.status = new app_model_status__WEBPACK_IMPORTED_MODULE_3__["Status"]();
        this.getAllStatusListByAppId();
    }
    ViewStatusComponent.prototype.getStatusListByAppId = function () {
        var appId = localStorage.getItem("appId");
        this.appURL = localStorage.getItem("appURL");
        return this.http.get(environments_environment__WEBPACK_IMPORTED_MODULE_2__["environment"].smartSafeAPIUrl + '/getAppStatusbyAppID?appId=' + appId, this.httpOptions);
    };
    ViewStatusComponent.prototype.getAllStatusListByAppId = function () {
        var _this = this;
        return this.getStatusListByAppId().
            subscribe(function (data) {
            console.log(data);
            _this.statuses = data;
            _this.spinner.hide();
        });
    };
    ViewStatusComponent.prototype.ngOnInit = function () {
        this.spinner.show();
        this.getAllStatusListByAppId();
    };
    ViewStatusComponent.ctorParameters = function () { return [
        { type: _angular_common_http__WEBPACK_IMPORTED_MODULE_5__["HttpClient"] },
        { type: _angular_router__WEBPACK_IMPORTED_MODULE_4__["Router"] },
        { type: ngx_spinner__WEBPACK_IMPORTED_MODULE_6__["NgxSpinnerService"] }
    ]; };
    ViewStatusComponent = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Component"])({
            selector: 'app-view-status',
            template: __webpack_require__(/*! raw-loader!./view-status.component.html */ "./node_modules/raw-loader/index.js!./src/app/dashboard/view-status/view-status.component.html"),
            styles: [__webpack_require__(/*! ./view-status.component.scss */ "./src/app/dashboard/view-status/view-status.component.scss")]
        }),
        tslib__WEBPACK_IMPORTED_MODULE_0__["__metadata"]("design:paramtypes", [_angular_common_http__WEBPACK_IMPORTED_MODULE_5__["HttpClient"], _angular_router__WEBPACK_IMPORTED_MODULE_4__["Router"], ngx_spinner__WEBPACK_IMPORTED_MODULE_6__["NgxSpinnerService"]])
    ], ViewStatusComponent);
    return ViewStatusComponent;
}());



/***/ }),

/***/ "./src/app/model/api.ts":
/*!******************************!*\
  !*** ./src/app/model/api.ts ***!
  \******************************/
/*! exports provided: Application */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "Application", function() { return Application; });
var Application = /** @class */ (function () {
    function Application() {
    }
    return Application;
}());



/***/ }),

/***/ "./src/app/model/status.ts":
/*!*********************************!*\
  !*** ./src/app/model/status.ts ***!
  \*********************************/
/*! exports provided: Status */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "Status", function() { return Status; });
/* harmony import */ var _user__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ./user */ "./src/app/model/user.ts");

var Status = /** @class */ (function () {
    function Status() {
        this.user = new _user__WEBPACK_IMPORTED_MODULE_0__["UserAccount"]();
    }
    return Status;
}());



/***/ })

}]);
//# sourceMappingURL=dashboard-dashboard-module.js.map