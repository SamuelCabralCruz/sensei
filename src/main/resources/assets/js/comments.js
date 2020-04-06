let emptyState = null;
let displayedSnippet = null;

function triggerWindowResize() {
    // PrismJs GitHub: https://github.com/PrismJS/prism/issues
    // IMPORTANT: For line number rendering PrismJs -> Issue #2284 PrismJs GitHub
    const event = document.createEvent('HTMLEvents');
    event.initEvent('resize', true, false);
    document.dispatchEvent(event);
    // IMPORTANT: Line highlight triggered before line number -> Issue #2285 PrismJs GitHub
    document.dispatchEvent(event);
}

function showCodeSnippet(key) {
    emptyState.hide();
    const correspondingCodeSnippet = $('div.snippets').find(`[key=${key}]`);
    if (displayedSnippet !== correspondingCodeSnippet) {
        if (displayedSnippet != null) displayedSnippet.hide();
        correspondingCodeSnippet.show();
        triggerWindowResize();
        displayedSnippet = correspondingCodeSnippet;
    }
}

function hideCodeSnippet() {
    emptyState.show();
    if (displayedSnippet != null) displayedSnippet.hide();
    displayedSnippet = null;
}

function noop() {
}

$(document).ready(function () {
    emptyState = $('div.snippets-panel-empty-state-layout');
    $('div.comments-panel-header').hover(hideCodeSnippet, noop);
    $('div.copyright').hover(hideCodeSnippet, noop);
    $('div.file-comment').each(function () {
        $(this).hover(function () {
            showCodeSnippet($(this).attr('key'))
        }, noop)
    });
});
