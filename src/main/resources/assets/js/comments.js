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
    const correspondingCodeSnippet = $('div.snippets').find(`[key=${key}]`);
    if (displayedSnippet !== correspondingCodeSnippet) {
        if (displayedSnippet != null) displayedSnippet.hide();
        correspondingCodeSnippet.show();
        triggerWindowResize();
        displayedSnippet = correspondingCodeSnippet;
    }
}

function fileCommentHoverIn() {
    return function () {
        showCodeSnippet($(this).attr('key'));
    };
}

function fileCommentHoverOut() {
    return function () {
    };
}

$(document).ready(function () {
    $('li.file-comment').each(function () {
        $(this).hover(fileCommentHoverIn(), fileCommentHoverOut());
    })
});
