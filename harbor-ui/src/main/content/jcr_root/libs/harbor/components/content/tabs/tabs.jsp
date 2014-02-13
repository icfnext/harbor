<%@include file="/libs/harbor/components/global.jsp" %>
<ct:component className="com.citytechinc.cq.harbor.components.content.tabs.Tabs" name="tabs"/>

<script type="text/javascript" src="http://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>

<c:set var="curtabs" scope="page" value="${tabs}"/>

<div class="tabs-container" class="tabs" id="${curtabs.uniqueId}-tabs-container">
    <ul class="nav nav-tabs" data-tabs="tabs">
        <c:forEach var="curTab" items="${curtabs.tabs}" varStatus="status">
            <c:set var="dataPathString" value="data-path='${curtabs.path}'"/>
            <li id="${curTab.uniqueId}" data-toggle="tab" name="${curTab.name}"
                class="tab-ui-item ${status.first? 'active':'' }"  ${isEditMode? dataPathString : ''}>
                <a href="#tabs-${curTab.uniqueId}" data-toggle="tab">${curTab.title}</a>
            </li>
        </c:forEach>
    </ul>

    <div class="tab-content">
        <c:forEach var="curTab" items="${curtabs.tabs}" varStatus="status">
            <div id="tabs-${curTab.uniqueId}" class="tab-pane ${status.first ? 'active' : ''}">
                <section class="${isEditMode || isDesignMode ? 'tab-par-author': ''}">
                    <cq:include path="${curTab.name}" resourceType="harbor/components/content/tabs/tab"/>
                </section>
            </div>
        </c:forEach>
    </div>
</div>

<c:if test="${isEditMode}">
    <script type="text/javascript">
        (function ($) {

            //todo: this is an author library, not a pub one.
            $(document).ready(function () {

                if (CQ.WCM.isEditMode()) {
                    // make tabs sortable in edit mode
                    $('#${curtabs.uniqueId}-tabs-container').children('ul.nav-tabs').each(function () {
                        var $this = $(this);

                        $this.sortable({
                            axis: 'x',
                            update: function (event, ui) {
                                // reorder tabs on back end on update
                                var $item = ui.item;
                                var name = $item.attr('name');

                                if (name) {
                                    var path = $item.data('path') + '/' + name;
                                    $.post(path, { ':order': $item.index() });
                                }
                            }
                        });
                    });
                }

            });
        })(jQuery);
    </script>
</c:if>
