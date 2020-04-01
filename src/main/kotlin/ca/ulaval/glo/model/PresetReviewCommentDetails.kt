package ca.ulaval.glo.model

import ca.ulaval.glo.model.CommentTag.CLEAN_CODE
import kotlin.reflect.full.declaredMemberProperties

val CLEAN_CODE_TAGS = mutableListOf(CLEAN_CODE)

class PresetReviewCommentDetails {
    val cleanCodeC1InappropriateInformation = ReviewCommentDetails(
        "[C1] - Inappropriate Information",
        "It is inappropriate for a comment to hold information better held in a different kind of system such as your source code control system, your issue tracking system, or any other record-keeping system. Change histories, for example, just clutter up source files with volumes of historical and uninteresting text. In general, meta-data such as authors, last-modified-date, SPR number, and so on should not appear in comments. Comments should be reserved for technical notes about the code and design.",
        CLEAN_CODE_TAGS
    )
    val cleanCodeC2ObsoleteComment = ReviewCommentDetails(
        "[C2] - Obsolete Comment",
        "A comment that has gotten old, irrelevant, and incorrect is obsolete. Comments get old quickly. It is best not to write a comment that will become obsolete. If you find an obsolete comment, it is best to update it or get rid of it as quickly as possible. Obsolete comments tend to migrate away from the code they once described. They become floating islands of irrelevance or misdirection.",
        CLEAN_CODE_TAGS
    )
    val cleanCodeC3RedundantComment = ReviewCommentDetails(
        "[C3] - Redundant Comment",
        "A comment that has gotten old, irrelevant, and incorrect is obsolete. Comments get old quickly. It is best not to write a comment that will become obsolete. If you find an obsolete comment, it is best to update it or get rid of it as quickly as possible. Obsolete comments tend to migrate away from the code they once described. They become floating islands of irrelevance or misdirection. Comments should say things that the code cannot say for itself.",
        CLEAN_CODE_TAGS
    )
    val cleanCodeC4PoorlyWrittenComment = ReviewCommentDetails(
        "[C4] - Poorly Written Comment",
        "A comment worth writing is worth writing well. If you are going to write a comment, take the time to make sure it is the best comment you can write. Choose your words carefully. Use correct grammar and punctuation. Don’t ramble. Don’t state the obvious. Be brief.",
        CLEAN_CODE_TAGS
    )
    val cleanCodeC5CommentedOutCode = ReviewCommentDetails(
        "[C5] - Commented-Out Code",
        "It makes me crazy to see stretches of code that are commented out. Who knows how old it is? Who knows whether or not it’s meaningful? Yet no one will delete it because everyone assumes someone else needs it or has plans for it. That code sits there and rots, getting less and less relevant with every passing day. It calls functions that no longer exist. It uses variables whose names have changed. It follows conventions that are obsolete. It pollutes the modules that contain it and distracts the people who try to read it. Commented-out code is an abomination.",
        CLEAN_CODE_TAGS
    )
    val cleanCodeE1BuildRequiresMoreThanOneStep = ReviewCommentDetails(
        "[E1] - Build Requires More Than One Step",
        "Building a project should be a single trivial operation. You should not have to check many little pieces out from source code control. You should not need a sequence of arcane commands or context dependent scripts in order to build the individual elements. You should not have to search near and far for all the various little extra JARs, XML files, and other artifacts that the system requires. You should be able to check out the system with one simple command and then issue one other simple command to build it.",
        CLEAN_CODE_TAGS
    )
    val cleanCodeE2TestsRequireMoreThanOneStep = ReviewCommentDetails(
        "[E2] - Tests Require More Than One Step",
        "You should be able to run all the unit tests with just one command. In the best case you can run all the tests by clicking one button in your IDE. In the worst case you should be able to issue a single simple command in a shell. Being able to run all the tests is so fundamental and so important that it should be quick, easy, and obvious to do.",
        CLEAN_CODE_TAGS
    )
    val cleanCodeF1TooManyArguments = ReviewCommentDetails(
        "[F1] - Too Many Arguments",
        "Functions should have a small number of arguments. No argument is best, followed by one, two, and three. More than three is very questionable and should be avoided with prejudice.",
        CLEAN_CODE_TAGS
    )
    val cleanCodeF2OutputArguments = ReviewCommentDetails(
        "[F2] - Output Arguments",
        "Output arguments are counterintuitive. Readers expect arguments to be inputs, not outputs. If your function must change the state of something, have it change the state of the object it is called on.",
        CLEAN_CODE_TAGS
    )
    val cleanCodeF3FlagArguments = ReviewCommentDetails(
        "[F3] - Flag Arguments",
        "Boolean arguments loudly declare that the function does more than one thing. They are confusing and should be eliminated.",
        CLEAN_CODE_TAGS
    )
    val cleanCodeF4DeadFunction = ReviewCommentDetails(
        "[F4] - Dead Function",
        "Methods that are never called should be discarded. Keeping dead code around is wasteful. Don’t be afraid to delete the function. Remember, your source code control system still remembers it.",
        CLEAN_CODE_TAGS
    )
    val cleanCodeG1MultipleLanguagesInOneSourceFile = ReviewCommentDetails(
        "[G1] - Multiple Languages in One Source File",
        "Today’s modern programming environments make it possible to put many different languages into a single source file. For example, a Java source file might contains snippets of XML, HTML, YAML, JavaDoc, English, JavaScript, and so on. For another example, in addition to HTML a JSP file might contain Java, a tag library syntax, English comments, Javadocs, XML, JavaScript, and so forth. This is confusing at best and carelessly sloppy at worst. The ideal is for a source file to contain one, and only one, language. Realistically, we will probably have to use more than one. But we should take pains to minimize both the number and extent of extra languages in our source files.",
        CLEAN_CODE_TAGS
    )
    val cleanCodeG2ObviousBehaviorIsUnimplemented = ReviewCommentDetails(
        "[G2] - Obvious Behavior Is Unimplemented",
        "Following “The Principle of Least Surprise”, any function of class should implement the behaviors that another programmer could reasonably expect. When an obvious behavior is not implemented, readers and users of the code can no longer depend on their intuition about function names. They lose their trust in the original author and must fall back on reading the details of the code.",
        CLEAN_CODE_TAGS
    )
    val cleanCodeG3IncorrectBehaviorAtTheBoundaries = ReviewCommentDetails(
        "[G3] - Incorrect Behavior at the Boundaries",
        "It seems obvious to say that code should behave correctly. The problem is that we seldom realize just how complicated correct behavior is. Developers often write functions that they think will work, and then trust their intuition rather than going to the effort to prove that their works in all the corner and boundary cases. There is no replacement for due diligence. Every boundary condition, every corner case, every quirk and exception represents something that can confound an elegant and intuitive algorithm. Don’t rely on your intuition. Look for every boundary condition and write a test for it.",
        CLEAN_CODE_TAGS
    )

    // TODO: finish list
    val cleanCodeT1InsufficientTests = ReviewCommentDetails(
        "[T1] - Insufficient Tests",
        "How many tests should be in a test suite? Unfortunately, the metric many programmers use is “That seems like enough”. A test suite should test everything that could possible break. The tests are insufficient so long as there are conditions that have not been explored by the tests or calculation that have not been validated.",
        CLEAN_CODE_TAGS
    )
    val cleanCodeT2UseACoverageTool = ReviewCommentDetails(
        "[T2] - Use a Coverage Tool!",
        "Coverage tools report gaps in your testing strategy. They make it easy to find modules, classes, and functions that are insufficiently tested. Most IDEs give you a visual indication, marking lines that are covered in green and those that are uncovered in red. This makes it quick and easy to find if or catch statements whose bodies haven’t been checked.",
        CLEAN_CODE_TAGS
    )
    val cleanCodeT3DontSkipTrivialTests = ReviewCommentDetails(
        "[T3] - Don't Skip Trivial Tests",
        "They are easy to write and their documentary value is higher than the code to produce them.",
        CLEAN_CODE_TAGS
    )
    val cleanCodeT4AnIgnoredTestIsAQuestionAboutAnAmbiguity = ReviewCommentDetails(
        "[T4] - An Ignored Test Is a Question about an Ambiguity",
        "Sometimes we are uncertain about a behavioral detail because the requirements are unclear. We can express our question about the requirements as a test that is commented out, or as a test that annotated with @Ignore. Which you choose depends upon whether the ambiguity is about something that would compile or not.",
        CLEAN_CODE_TAGS
    )
    val cleanCodeT5TestBoundaryConditions = ReviewCommentDetails(
        "[T5] - Test Boundary Conditions",
        "Take special care to test boundary conditions. We often get the middle of an algorithm right but misjudge the boundaries.",
        CLEAN_CODE_TAGS
    )
    val cleanCodeT6ExhaustivelyTestNearBugs = ReviewCommentDetails(
        "[T6] - Exhaustively Test Near Bugs",
        "Bugs tend to congregate. When you find a bug in a function, it is wise to do an exhaustive test of that function. You’ll probably find that the bug was not alone.",
        CLEAN_CODE_TAGS
    )
    val cleanCodeT7PatternsOfFailureAreRevealing = ReviewCommentDetails(
        "[T7] - Patterns of Failure Are Revealing",
        "Sometimes you can diagnose a problem by finding patterns in the way the test cases fail. This is another argument for making the test cases as complete as possible. Complete test cases, ordered in a reasonable way, expose patterns. As a simple example, suppose you noticed that all tests with an input larger than five characters failed? or what if any test that passed a negative number into a second argument of a function failed? Sometimes just seeing the pattern of red and green on the test report is enough to spark the “Aha!” that leads to the solution. Look page at page 267 to see an interesting example of this in the SerialDate example.",
        CLEAN_CODE_TAGS
    )
    val cleanCodeT8TestCoveragePatternsCanBeRevealing = ReviewCommentDetails(
        "[T8] - Test Coverage Patterns Can Be Revealing",
        "Looking at the code that is or is not executed by the passing tests gives clues to why the failing tests fail.",
        CLEAN_CODE_TAGS
    )
    val cleanCodeT9TestsShouldBeFast = ReviewCommentDetails(
        "[T9] - Tests Should Be Fast",
        "A slow test is a test that won’t get run. When things get tight, it’s the slow tests that will be dropped from the suite. So do what you must to keep your tests fast.",
        CLEAN_CODE_TAGS
    )
}

fun getAllPresetReviewCommentDetails(): Array<ReviewCommentDetails> {
    return PresetReviewCommentDetails::class.declaredMemberProperties.map(fun(x): ReviewCommentDetails {
        return x.get(PresetReviewCommentDetails()) as ReviewCommentDetails
    }).toTypedArray()
}
