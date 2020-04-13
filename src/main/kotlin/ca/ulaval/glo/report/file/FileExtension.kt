package ca.ulaval.glo.report.file

enum class FileExtension(val values: List<String>, val brush: String) {
    BASH(listOf("sh"), "bash"),
    C(listOf("c"), "c"),
    CLOJURE(listOf("clojure", "clj"), "clojure"),
    CPP(listOf("cpp"), "cpp"),
    CSS(listOf("css"), "css"),
    DOTNET(listOf("cs"), "dotnet"),
    GRAPHQL(listOf("graphql"), "graphql"),
    HTML(listOf("html"), "html"),
    JAVA(listOf("java"), "java"),
    JAVASCRIPT(listOf("js"), "javascript"),
    JSON(listOf("json"), "json"),
    KOTLIN(listOf("kt"), "kotlin"),
    LATEX(listOf("tex"), "latex"),
    LUA(listOf("lua"), "lua"),
    MARKDOWN(listOf("md"), "markdown"),
    PERL(listOf("pl"), "perl"),
    PLAIN(listOf("txt"), "none"),
    PYTHON(listOf("py"), "python"),
    RUBY(listOf("rb"), "ruby"),
    RUST(listOf("rs"), "rust"),
    SASS(listOf("sass"), "sass"),
    SCSS(listOf("scss"), "scss"),
    SQL(listOf("sql"), "sql"),
    SWIFT(listOf("swift"), "swift"),
    TYPESCRIPT(listOf("ts"), "typescript"),
    XML(listOf("xml"), "xml"),
    YAML(listOf("yaml", "yml"), "yaml");

    fun matches(extension: String): Boolean {
        return values.contains(extension)
    }

    companion object {
        fun fromExtension(extension: String): FileExtension {
            return values().find(fun(fileExtension: FileExtension): Boolean {
                return fileExtension.values.contains(extension)
            }) ?: PLAIN
        }
    }
}
