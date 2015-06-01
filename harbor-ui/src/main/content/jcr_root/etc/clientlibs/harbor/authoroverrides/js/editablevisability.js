/*
 * Override the observe method of CQ.wcm.EditBar adding
 * an observeWidth (implemented in 5.5) and
 * observe visibility to support hiding and
 * showing of editables
 */

if ( typeof CQ !== 'undefined' ) {
    CQ.Ext.override(
        CQ.wcm.EditBar,
        {
            /**
             * Call the originally defined observe function for
             * the EditBar
             * along with visibility and width observations
             * @private
             */
            observe: function (originalFunction) {
                return function () {
                    this.observeVisibility();

                    originalFunction.apply(this);

                    //this.observeWidth();

                };
            }(CQ.wcm.EditBar.prototype.observe),

            /**
             * Note - observeWidth is unnecessary if you are
             * running
             * CQ5.5+

             observeWidth : function() {
      var barWidth = this.el.getWidth();
      var elementWidth = this.element.getWidth();
      if (elementWidth != 0 && barWidth != elementWidth) {
        this.el.setStyle("width", elementWidth + "px");
      }
    },*/

            /**
             * Base the visibility of the EditBar on the
             * visibility of the
             * element which the EditBar represents editing upon
             */
            observeVisibility: function () {

                var editableIsVisible = this.isVisible();
                var underlyingElementIsVisible = jQuery(this.element.dom).is(':visible');
                var editableIsNotInContext = this.hasOwnProperty('harborInCurrentContext') && this.harborInCurrentContext === false;

                if (editableIsVisible && ( !underlyingElementIsVisible || editableIsNotInContext )) {
                    this.hide(true);
                }
                else if (!editableIsVisible && ( !editableIsNotInContext && underlyingElementIsVisible )) {
                    /*
                     * When an author moves into preview mode from edit - edit bars end up being shown, because they still exist
                     * on the page until the page is refreshed.  This check stops that from happening.
                     */
                    if (CQ.WCM.isEditMode() || CQ.WCM.isDesignMode()) {
                        this.show();
                    }
                }
            }
        }
    );

    /*
     * Override the observe method of CQ.wcm.EditRollover
     * adding an observe visibility to support hiding and
     * showing of editables
     */
    CQ.Ext.override(
        CQ.wcm.EditRollover,
        {
            /**
             * Call the originally defined observe function
             * for the EditRollover and then perform the
             * visibility observation
             * @private
             */
            observe: function (originalFunction) {
                return function () {
                    this.observeVisibility();

                    originalFunction.apply(this);

                };
            }(CQ.wcm.EditRollover.prototype.observe),


            /**
             * Base the visibility of the EditRollover on
             * the visibility of the element which the
             * EditRollover represents editing upon
             */
            observeVisibility: function () {

                var editableIsVisible = !this.hidden && !this.elementHidden;
                var underlyingElementIsVisible = jQuery(this.element.dom).is(':visible');
                var editableIsNotInContext = this.hasOwnProperty('harborInCurrentContext') && this.harborInCurrentContext === false;

                if (editableIsVisible && ( !underlyingElementIsVisible || editableIsNotInContext )) {
                    this.hide(true);
                }
                else if (this.hidden && ( !editableIsNotInContext && underlyingElementIsVisible )) {
                    this.show();
                }
            }
        }
    );
}
