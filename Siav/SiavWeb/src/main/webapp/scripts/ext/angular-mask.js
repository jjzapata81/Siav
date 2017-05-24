/*!
 * angular-ui-mask
 * https://github.com/angular-ui/ui-mask
 * Version: 1.7.2 - 2016-01-29T01:38:58.683Z
 * License: MIT
 */
!function(){"use strict";angular.module("ui.mask",[]).value("uiMaskConfig",{maskDefinitions:{9:/\d/,A:/[a-zA-Z]/,"*":/[a-zA-Z0-9]/},clearOnBlur:!0,clearOnBlurPlaceholder:!1,eventsToHandle:["input","keyup","click","focus"]}).directive("uiMask",["uiMaskConfig",function(e){function n(e){return e===document.activeElement&&(!document.hasFocus||document.hasFocus())&&!!(e.type||e.href||~e.tabIndex)}return{priority:100,require:"ngModel",restrict:"A",compile:function(){var t=e;return function(e,i,r,a){function u(e){return angular.isDefined(e)?($(e),q?(h(),d(),!0):f()):f()}function l(e){e&&(C=e,!q||0===i.val().length&&angular.isDefined(r.placeholder)||i.val(m(p(i.val()))))}function o(){return u(r.uiMask)}function c(e){return q?(T=p(e||""),H=v(T),a.$setValidity("mask",H),H&&T.length?m(T):void 0):e}function s(e){return q?(T=p(e||""),H=v(T),a.$viewValue=T.length?m(T):"",a.$setValidity("mask",H),H?J?a.$viewValue:T:void 0):e}function f(){return q=!1,g(),angular.isDefined(K)?i.attr("placeholder",K):i.removeAttr("placeholder"),angular.isDefined(W)?i.attr("maxlength",W):i.removeAttr("maxlength"),i.val(a.$modelValue),a.$viewValue=a.$modelValue,!1}function h(){T=F=p(a.$modelValue||""),B=z=m(T),H=v(T),r.maxlength&&i.attr("maxlength",2*S[S.length-1]),K||i.attr("placeholder",C);for(var e=a.$modelValue,n=a.$formatters.length;n--;)e=a.$formatters[n](e);a.$viewValue=e||"",a.$render()}function d(){I||(i.bind("blur",y),i.bind("mousedown mouseup",E),i.bind("keydown",x),i.bind(Q.eventsToHandle.join(" "),O),I=!0)}function g(){I&&(i.unbind("blur",y),i.unbind("mousedown",E),i.unbind("mouseup",E),i.unbind("keydown",x),i.unbind("input",O),i.unbind("keyup",O),i.unbind("click",O),i.unbind("focus",O),I=!1)}function v(e){return e.length?e.length>=R:!0}function p(e){var n,t,r="",a=i[0],u=_.slice(),l=N,o=l+A(a),c="";return e=e.toString(),n=0,t=e.length-C.length,angular.forEach(j,function(i){var r=i.position;r>=l&&o>r||(r>=l&&(r+=t),e.substring(r,r+i.value.length)===i.value&&(c+=e.slice(n,r),n=r+i.value.length))}),e=c+e.slice(n),angular.forEach(e.split(""),function(e){u.length&&u[0].test(e)&&(r+=e,u.shift())}),r}function m(e){var n="",t=S.slice();return angular.forEach(C.split(""),function(i,r){e.length&&r===t[0]?(n+=e.charAt(0)||"_",e=e.substr(1),t.shift()):n+=i}),n}function b(e){var n,t=angular.isDefined(r.uiMaskPlaceholder)?r.uiMaskPlaceholder:r.placeholder;return angular.isDefined(t)&&t[e]?t[e]:(n=angular.isDefined(r.uiMaskPlaceholderChar)&&r.uiMaskPlaceholderChar?r.uiMaskPlaceholderChar:"_","space"===n.toLowerCase()?" ":n[0])}function k(){var e,n,t=C.split("");S&&!isNaN(S[0])&&angular.forEach(S,function(e){t[e]="_"}),e=t.join(""),n=e.replace(/[_]+/g,"_").replace(/([^_]+)([a-zA-Z0-9])([^_])/g,"$1$2_$3").split("_"),n=n.filter(function(e){return""!==e});var i=0;return n.map(function(n){var t=e.indexOf(n,i);return i=t+1,{value:n,position:t}})}function $(e){var n=0;if(S=[],_=[],C="",angular.isString(e)){R=0;var t=!1,i=0,r=e.split("");angular.forEach(r,function(e,r){Q.maskDefinitions[e]?(S.push(n),C+=b(r-i),_.push(Q.maskDefinitions[e]),n++,t||R++,t=!1):"?"===e?(t=!0,i++):(C+=e,n++)})}S.push(S.slice().pop()+1),j=k(),q=S.length>1?!0:!1}function y(){(Q.clearOnBlur||Q.clearOnBlurPlaceholder&&0===T.length&&r.placeholder)&&(N=0,Z=0,H&&0!==T.length||(B="",i.val(""),e.$apply(function(){a.$viewValue=""}))),T!==U&&w(i[0]),U=T}function w(e){var n;angular.isFunction(window.Event)&&!e.fireEvent?(n=new Event("change",{view:window,bubbles:!0,cancelable:!1}),e.dispatchEvent(n)):"createEvent"in document?(n=document.createEvent("HTMLEvents"),n.initEvent("change",!1,!0),e.dispatchEvent(n)):e.fireEvent&&e.fireEvent("onchange")}function E(e){"mousedown"===e.type?i.bind("mouseout",M):i.unbind("mouseout",M)}function M(){Z=A(this),i.unbind("mouseout",M)}function x(e){var n=8===e.which,t=D(this)-1||0;if(n){for(;t>=0;){if(V(t)){P(this,t+1);break}t--}L=-1===t}}function O(n){n=n||{};var t=n.which,r=n.type;if(16!==t&&91!==t){var u,l=i.val(),o=z,c=!1,s=p(l),f=F,h=D(this)||0,d=N||0,g=h-d,v=S[0],b=S[s.length]||S.slice().shift(),k=Z||0,$=A(this)>0,y=k>0,w=l.length>o.length||k&&l.length>o.length-k,E=l.length<o.length||k&&l.length===o.length-k,M=t>=37&&40>=t&&n.shiftKey,x=37===t,O=8===t||"keyup"!==r&&E&&-1===g,_=46===t||"keyup"!==r&&E&&0===g&&!y,j=(x||O||"click"===r)&&h>v;if(Z=A(this),!M&&(!$||"click"!==r&&"keyup"!==r)){if(O&&L)return i.val(C),e.$apply(function(){a.$setViewValue("")}),void P(this,d);if("input"===r&&E&&!y&&s===f){for(;O&&h>v&&!V(h);)h--;for(;_&&b>h&&-1===S.indexOf(h);)h++;var R=S.indexOf(h);s=s.substring(0,R)+s.substring(R+1),s!==f&&(c=!0)}for(u=m(s),z=u,F=s,!c&&l.length>u.length&&(c=!0),i.val(u),c&&e.$apply(function(){a.$setViewValue(u)}),w&&v>=h&&(h=v+1),j&&h--,h=h>b?b:v>h?v:h;!V(h)&&h>v&&b>h;)h+=j?-1:1;(j&&b>h||w&&!V(d))&&h++,N=h,P(this,h)}}}function V(e){return S.indexOf(e)>-1}function D(e){if(!e)return 0;if(void 0!==e.selectionStart)return e.selectionStart;if(document.selection&&n(i[0])){e.focus();var t=document.selection.createRange();return t.moveStart("character",e.value?-e.value.length:0),t.text.length}return 0}function P(e,t){if(!e)return 0;if(0!==e.offsetWidth&&0!==e.offsetHeight)if(e.setSelectionRange)n(i[0])&&(e.focus(),e.setSelectionRange(t,t));else if(e.createTextRange){var r=e.createTextRange();r.collapse(!0),r.moveEnd("character",t),r.moveStart("character",t),r.select()}}function A(e){return e?void 0!==e.selectionStart?e.selectionEnd-e.selectionStart:document.selection?document.selection.createRange().text.length:0:0}var S,_,C,j,R,T,B,H,z,F,N,Z,L,q=!1,I=!1,K=r.placeholder,W=r.maxlength,G=a.$isEmpty;a.$isEmpty=function(e){return G(q?p(e||""):e)};var J=!1;r.$observe("modelViewValue",function(e){"true"===e&&(J=!0)});var Q={};r.uiOptions?(Q=e.$eval("["+r.uiOptions+"]"),Q=angular.isObject(Q[0])?function(e,n){for(var t in e)Object.prototype.hasOwnProperty.call(e,t)&&(void 0===n[t]?n[t]=angular.copy(e[t]):angular.isObject(n[t])&&!angular.isArray(n[t])&&(n[t]=angular.extend({},e[t],n[t])));return n}(t,Q[0]):t):Q=t,r.$observe("uiMask",u),angular.isDefined(r.uiMaskPlaceholder)?r.$observe("uiMaskPlaceholder",l):r.$observe("placeholder",l),angular.isDefined(r.uiMaskPlaceholderChar)&&r.$observe("uiMaskPlaceholderChar",o),a.$formatters.push(c),a.$parsers.unshift(s);var U=i.val();i.bind("mousedown mouseup",E),Array.prototype.indexOf||(Array.prototype.indexOf=function(e){if(null===this)throw new TypeError;var n=Object(this),t=n.length>>>0;if(0===t)return-1;var i=0;if(arguments.length>1&&(i=Number(arguments[1]),i!==i?i=0:0!==i&&i!==1/0&&i!==-(1/0)&&(i=(i>0||-1)*Math.floor(Math.abs(i)))),i>=t)return-1;for(var r=i>=0?i:Math.max(t-Math.abs(i),0);t>r;r++)if(r in n&&n[r]===e)return r;return-1})}}}}])}();