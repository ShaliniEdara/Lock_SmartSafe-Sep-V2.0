(window["webpackJsonp"] = window["webpackJsonp"] || []).push([["common"],{

/***/ "./src/app/model/role.ts":
/*!*******************************!*\
  !*** ./src/app/model/role.ts ***!
  \*******************************/
/*! exports provided: Role */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "Role", function() { return Role; });
var Role = /** @class */ (function () {
    function Role() {
    }
    return Role;
}());



/***/ }),

/***/ "./src/app/model/user.ts":
/*!*******************************!*\
  !*** ./src/app/model/user.ts ***!
  \*******************************/
/*! exports provided: UserAccount */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "UserAccount", function() { return UserAccount; });
var UserAccount = /** @class */ (function () {
    function UserAccount() {
    }
    return UserAccount;
}());



/***/ }),

/***/ "./src/app/service/toastr.service.ts":
/*!*******************************************!*\
  !*** ./src/app/service/toastr.service.ts ***!
  \*******************************************/
/*! exports provided: NGXToastrService */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "NGXToastrService", function() { return NGXToastrService; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var ngx_toastr__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ngx-toastr */ "./node_modules/ngx-toastr/fesm5/ngx-toastr.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");



var NGXToastrService = /** @class */ (function () {
    function NGXToastrService(toastr) {
        this.toastr = toastr;
    }
    // Success Type
    NGXToastrService.prototype.addSuccess = function () {
        this.toastr.success('Sucessfully added!');
    };
    NGXToastrService.prototype.updateSuccess = function () {
        this.toastr.success('Sucessfully updated!');
    };
    NGXToastrService.prototype.loginSuccess = function () {
        this.toastr.success('Sucessfully Login!');
    };
    NGXToastrService.prototype.passwordChangeSuccess = function () {
        this.toastr.success('Password Changed Sucessfully !');
    };
    NGXToastrService.prototype.typeDelete = function () {
        this.toastr.success('Deleted Sucessfully !');
    };
    // Success Type
    NGXToastrService.prototype.typeInfo = function () {
        this.toastr.info('We do have the Kapua suite available.', 'Turtle Bay Resort');
    };
    // Success Type
    NGXToastrService.prototype.typeWarning = function () {
        this.toastr.warning('Sorry! Failed.');
    };
    // Success Type
    NGXToastrService.prototype.typeError = function () {
        this.toastr.error('I do not think that word means what you think it means.', 'Inconceivable!');
    };
    NGXToastrService.prototype.showMessage = function (message) {
        this.toastr.error(message);
    };
    // Custom Type
    NGXToastrService.prototype.typeCustom = function () {
        this.toastr.success('<span class="text-danger">Message in red.</span>', null, { enableHtml: true });
    };
    //Progress bar
    NGXToastrService.prototype.progressBar = function () {
        this.toastr.info('We do have the Kapua suite available.', 'Turtle Bay Resort', { "progressBar": true });
    };
    // Timeout
    NGXToastrService.prototype.timeout = function () {
        this.toastr.error('I do not think that word means what you think it means.', 'Timeout!', { "timeOut": 2000 });
    };
    //Dismiss toastr on Click
    NGXToastrService.prototype.dismissToastOnClick = function () {
        this.toastr.info('We do have the Kapua suite available.', 'Turtle Bay Resort', { "tapToDismiss": true });
    };
    // Remove current toasts using animation
    NGXToastrService.prototype.clearToast = function () {
        this.toastr.clear();
    };
    // Show close button
    NGXToastrService.prototype.showCloseButton = function () {
        this.toastr.info('Have fun storming the castle!', 'Miracle Max Says', { closeButton: true });
    };
    // Enable  HTML
    NGXToastrService.prototype.enableHtml = function () {
        this.toastr.info('<i>Have fun <b>storming</b> the castle!</i>', 'Miracle Max Says', { enableHtml: true });
    };
    // Title Class
    NGXToastrService.prototype.titleClass = function () {
        this.toastr.info('Have fun storming the castle!', 'Miracle Max Says', { titleClass: 'h3' });
    };
    // Message Class
    NGXToastrService.prototype.messageClass = function () {
        this.toastr.info('Have fun storming the castle!', 'Miracle Max Says', { messageClass: 'text-uppercase' });
    };
    NGXToastrService.ctorParameters = function () { return [
        { type: ngx_toastr__WEBPACK_IMPORTED_MODULE_1__["ToastrService"] }
    ]; };
    NGXToastrService = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_2__["Injectable"])(),
        tslib__WEBPACK_IMPORTED_MODULE_0__["__metadata"]("design:paramtypes", [ngx_toastr__WEBPACK_IMPORTED_MODULE_1__["ToastrService"]])
    ], NGXToastrService);
    return NGXToastrService;
}());



/***/ })

}]);
//# sourceMappingURL=common.js.map