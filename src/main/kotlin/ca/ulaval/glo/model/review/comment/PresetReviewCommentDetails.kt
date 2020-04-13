package ca.ulaval.glo.model.review.comment

import ca.ulaval.glo.model.review.comment.CommentTag.ARCHITECTURE
import ca.ulaval.glo.model.review.comment.CommentTag.CLEAN_CODE
import kotlin.reflect.full.declaredMemberProperties

class PresetReviewCommentDetails {
    val cleanCodeC01InappropriateInformation = createCleanCodeComment(
        "[C01] - Inappropriate Information",
        "It is inappropriate for a comment to hold information better held in a different kind of system such as your source code control system, your issue tracking system, or any other record-keeping system. Change histories, for example, just clutter up source files with volumes of historical and uninteresting text. In general, meta-data such as authors, last-modified-date, SPR number, and so on should not appear in comments. Comments should be reserved for technical notes about the code and design."
    )
    val cleanCodeC02ObsoleteComment = createCleanCodeComment(
        "[C02] - Obsolete Comment",
        "A comment that has gotten old, irrelevant, and incorrect is obsolete. Comments get old quickly. It is best not to write a comment that will become obsolete. If you find an obsolete comment, it is best to update it or get rid of it as quickly as possible. Obsolete comments tend to migrate away from the code they once described. They become floating islands of irrelevance or misdirection."
    )
    val cleanCodeC03RedundantComment = createCleanCodeComment(
        "[C03] - Redundant Comment",
       "A comment is redundant if it describes something that adequately describes itself. Comments should say things that the code cannot say for itself."
    )
    val cleanCodeC04PoorlyWrittenComment = createCleanCodeComment(
        "[C04] - Poorly Written Comment",
        "A comment worth writing is worth writing well. If you are going to write a comment, take the time to make sure it is the best comment you can write. Choose your words carefully. Use correct grammar and punctuation. Don’t ramble. Don’t state the obvious. Be brief."
    )
    val cleanCodeC05CommentedOutCode = createCleanCodeComment(
        "[C05] - Commented-Out Code",
        "It makes me crazy to see stretches of code that are commented out. Who knows how old it is? Who knows whether or not it’s meaningful? Yet no one will delete it because everyone assumes someone else needs it or has plans for it. That code sits there and rots, getting less and less relevant with every passing day. It calls functions that no longer exist. It uses variables whose names have changed. It follows conventions that are obsolete. It pollutes the modules that contain it and distracts the people who try to read it. Commented-out code is an abomination."
    )
    val cleanCodeE01BuildRequiresMoreThanOneStep = createCleanCodeComment(
        "[E01] - Build Requires More Than One Step",
        "Building a project should be a single trivial operation. You should not have to check many little pieces out from source code control. You should not need a sequence of arcane commands or context dependent scripts in order to build the individual elements. You should not have to search near and far for all the various little extra JARs, XML files, and other artifacts that the system requires. You should be able to check out the system with one simple command and then issue one other simple command to build it."
    )
    val cleanCodeE02TestsRequireMoreThanOneStep = createCleanCodeComment(
        "[E02] - Tests Require More Than One Step",
        "You should be able to run all the unit tests with just one command. In the best case you can run all the tests by clicking one button in your IDE. In the worst case you should be able to issue a single simple command in a shell. Being able to run all the tests is so fundamental and so important that it should be quick, easy, and obvious to do."
    )
    val cleanCodeF01TooManyArguments = createCleanCodeComment(
        "[F01] - Too Many Arguments",
        "Functions should have a small number of arguments. No argument is best, followed by one, two, and three. More than three is very questionable and should be avoided with prejudice."
    )
    val cleanCodeF02OutputArguments = createCleanCodeComment(
        "[F02] - Output Arguments",
        "Output arguments are counterintuitive. Readers expect arguments to be inputs, not outputs. If your function must change the state of something, have it change the state of the object it is called on."
    )
    val cleanCodeF03FlagArguments = createCleanCodeComment(
        "[F03] - Flag Arguments",
        "Boolean arguments loudly declare that the function does more than one thing. They are confusing and should be eliminated."
    )
    val cleanCodeF04DeadFunction = createCleanCodeComment(
        "[F04] - Dead Function",
        "Methods that are never called should be discarded. Keeping dead code around is wasteful. Don’t be afraid to delete the function. Remember, your source code control system still remembers it."
    )
    val cleanCodeG01MultipleLanguagesInOneSourceFile = createCleanCodeComment(
        "[G01] - Multiple Languages in One Source File",
        "Today’s modern programming environments make it possible to put many different languages into a single source file. For example, a Java source file might contains snippets of XML, HTML, YAML, JavaDoc, English, JavaScript, and so on. For another example, in addition to HTML a JSP file might contain Java, a tag library syntax, English comments, Javadocs, XML, JavaScript, and so forth. This is confusing at best and carelessly sloppy at worst. The ideal is for a source file to contain one, and only one, language. Realistically, we will probably have to use more than one. But we should take pains to minimize both the number and extent of extra languages in our source files."
    )
    val cleanCodeG02ObviousBehaviorIsUnimplemented = createCleanCodeComment(
        "[G02] - Obvious Behavior Is Unimplemented",
        "Following “The Principle of Least Surprise”, any function of class should implement the behaviors that another programmer could reasonably expect. When an obvious behavior is not implemented, readers and users of the code can no longer depend on their intuition about function names. They lose their trust in the original author and must fall back on reading the details of the code."
    )
    val cleanCodeG03IncorrectBehaviorAtTheBoundaries = createCleanCodeComment(
        "[G03] - Incorrect Behavior at the Boundaries",
        "It seems obvious to say that code should behave correctly. The problem is that we seldom realize just how complicated correct behavior is. Developers often write functions that they think will work, and then trust their intuition rather than going to the effort to prove that their works in all the corner and boundary cases. There is no replacement for due diligence. Every boundary condition, every corner case, every quirk and exception represents something that can confound an elegant and intuitive algorithm. Don’t rely on your intuition. Look for every boundary condition and write a test for it."
    )
    val cleanCodeG04OverriddenSafeties = createCleanCodeComment(
        "[G04] - Overridden Safeties",
        "Chernobyl melted down because the plan manager overrode each of the safety mechanisms one by one. The safeties were making it inconvenient to run an experiment. The result was that the experiment did not get run, and the world saw it’s first major civilian nuclear catastrophe.\nIt is risky to override safeties. Exerting manual control over serialVersionUID may be necessary, but it is always risky. Turning off certain compiler warnings (or all warnings!) may help you get the build to succeed, but at the risk of endless debugging sessions. Turning off failing tests and telling yourself you’ll get them to pass later is as bad as pretending your credit cards are free money."
    )
    val cleanCodeG05Duplication = createCleanCodeComment(
        "[G05] - Duplication",
        "This is one of the most important rules in this book, and you should take it very seriously. Virtually every author who writes about software design mentions this rule. Dave Thomas and Andy Hunt called it the DRY1 principle (Don’t Repeat Yourself). Kent Beck made it one of the core principles of Extreme Programming and call it: “Once, and only once.” Ron Jeffries ranks this rule second, just below getting all the tests to pass.\nEvery time you see duplication in the code, it represents a missed opportunity for abstraction. That duplication could probably become a subroutine or perhaps another class outright. By folding the duplication into such an abstraction, you increase the vocabulary of the language of your design. Other programmers can use the abstract facilities you create. Coding becomes faster and less error prone because you have raised the abstraction level.\nThe most obvious form of duplication is when you have clumps of identical code that look like some programmers went wild with the mouse, pasting the same code over and over again. These should be replaced with simple methods.\nA more subtle form is the switch/case or if/else chain that appears again and again in various modules, always testing for the same set of conditions. These should be replaced with polymorphism.\nStill more subtle are the modules that have similar algorithms, but that don’t share similar lines of code. This is still duplication and should be addressed by using the Template Method2 or Strategy pattern3.\nIndeed, most of the design patterns that have appeared in the last fifteen years are simply well-known ways to eliminate duplication. So too the Codd Normal Forms are a strategy for eliminating duplication. Not surprisingly, so is structured programming.\nI think the point has been made. Find and eliminate duplication wherever you can."
    )
    val cleanCodeG06CodeAtWrongLevelOfAbstraction = createCleanCodeComment(
        "[G06] - Code at Wrong Level of Abstraction",
        "It is important to create abstractions that separate higher level general concepts from lower level detailed concepts. Sometimes we do this by creating abstract classes to hold the higher level concepts and derivatives to hold the lower level concepts, When we do this, we need to make sure that the separation is complete. We want all the lower level concepts to be in the derivatives and all the higher level concepts to be in the base class.\nFor example, constants, variables, or utility functions that pertain only to the detailed implementation should not be present in the base class.\nThis rule also pertains to source files, components, and modules. Good software design requires that we separate concepts at different levels and place them in different containers. Sometimes these containers are base classes or derivatives and sometimes they are source files, modules, or components. Whatever the case may be, the separation needs to be complete. We don’t want lower and higher level concepts mixed together.\nThe point is that you cannot lie or fake your way out of misplaced abstraction. Isolating abstractions is one of the hardest things that software developers do, and there is no quick fix when you get it wrong."
    )
    val cleanCodeG07BaseClassesDependingOnTheirDerivatives = createCleanCodeComment(
        "[G07] - Base Classes Depending on Their Derivatives",
        "The most common reason for partitioning concepts into base and derivative classes is so that the higher level base class concepts can be independent of the lower level derivative class concepts. Therefore, when we see base classes mentioning the names of their derivatives, we suspect a problem. In general, base classes should know nothing about their derivatives.\nThere are exceptions to this rule, of course. Sometimes the number of derivatives is strictly fixed, and the base class has code that selects between the derivatives. We see this a lot in finite state machine implementations. However, in that case the derivatives and base class are strongly coupled and always deploy together in the same jar file. In the general case we want to be able to deploy derivatives and bases in different jar files.\nDeploying derivatives and bases in different jar files and making sure the base jar files know nothing about the contents of the derivative jar files allows us to deploy our systems in discrete and independent components. When such components are modified, they can be redeployed without having to redeploy the base components. This means that the impact of change is greatly lessened, and maintaining systems in the field is made much simpler."
    )
    val cleanCodeG08TooMuchInformation = createCleanCodeComment(
        "[G08] - Too Much Information",
        "Well-defined modules have very small interfaces that allow you to do a lot with a little. Poorly defined modules have wide and deep interfaces that force you to use many different gestures to get simple things done. A well-defined interface does not offer very many functions to depend upon, so coupling is low. A poorly defined interface provides lots of functions that you must call, so coupling is high.\nGood software developers learn to limit what they expose at the interfaces of their classes and modules. The fewer methods a class has, the better, The fewer variables a function knows about, the better. The fewer instance variables a class has, the better.\nHide your data. Hide your utility functions. Hide your constants and your temporaries. Don’t create classes with lots of methods or lots of instance variables. Don’t create lots of protected variables and functions for your subclasses. Concentrate on keeping interfaces very tight and small. Help keep coupling low by limiting information."
    )
    val cleanCodeG09DeadCode = createCleanCodeComment(
        "[G09] - Dead Code",
        "Dead code is code that isn’t executed. You find it in the body of an if statement that checks for a condition that can’t happen. You find it in the catch block of a try that never throws. You find it in little utility methods that are never called or switch/case conditions that never occur.\nThe problem with dead code is that after awhile it starts to smell. The older it is, the stronger and sourer the odor becomes. This is because dead code is not completely updated when designs change. It still compiles, but it does not follow newer conventions or rules. It was written at a time when the system was different. When you find dead code, do the right thing. Give it a decent burial. Delete it from the system."
    )
    val cleanCodeG10VerticalSeparation = createCleanCodeComment(
        "[G10] - Vertical Separation",
        "Variables and function should be defined close to where they are used. Local variables should be declared just above their first usage and should have a small vertical scope. We don’t want local variables declared hundreds of lines distant from their usages.\nPrivate functions should be defined just below their first usage. Private functions belong to the scope of the whole class, but we’d still like to limit the vertical distance between the invocations and definitions. Finding a private function should just be a matter of scanning downward from the first usage."
    )
    val cleanCodeG11Inconsistency = createCleanCodeComment(
        "[G11] - Inconsistency",
        "If you do something a certain way, do all similar things in the same way. This goes back to the principle of least surprise. Be careful with the conventions you choose, and once chosen, be careful to continue to follow them.\nIf within a particular function you use a variable names response to hold an HttpServletResponse, then use the same variable name consistently in the other functions that use HttpServletResponse objects. If you name a method processVerificationRequest, then use a similar name, such as processDeletionRequest, for the methods that process other kinds of requests.\nSimple consistency like this, when reliably applied, can make code much easier to read and modify."
    )
    val cleanCodeG12Clutter = createCleanCodeComment(
        "[G12] - Clutter",
        "Of what use is a default constructor with no implementation? All it serves to do is clutter up the code with meaningless artifacts. Variables that aren’t used, functions that are never called, comments that add no information, and so forth. All these things are clutter and should be removed. Keep your source files clean, well organized, and free of clutter."
    )
    val cleanCodeG13ArtificialCoupling = createCleanCodeComment(
        "[G13] - Artificial Coupling",
        "Things that don’t depend upon each other should not be artificially coupled. For example, general enums should not be contained within more specific classes because this forces the whole application to know about these more specific classes. The same goes for general purpose static functions being declared in specific classes.\nIn general an artificial coupling is a coupling between two modules that serves no direct purpose. It is a result of putting a variable, constant, or function in a temporarily convenient, though inappropriate, location. This is lazy and careless.\nTake the time to figure out where functions, constants, and variables ought to be declared. Don’t just toss them in the most convenient place at hand and then leave them there."
    )
    val cleanCodeG14FeatureEnvy = createCleanCodeComment(
        "[G14] - Feature Envy",
        "This is one of Martin Fowler’s code smells. The methods of a class should be interested in the variables and functions of the class they belong to, and not the variables and functions of other classes. When a method uses accessors and mutators of some other object to manipulate the data within that object, then it envies the scope of the class of that other object. It wishes that it were inside that other class so that it could have direct access to the variables it is manipulating."
    )
    val cleanCodeG15SelectorArguments = createCleanCodeComment(
        "[G15] - Selector Arguments",
        "There is hardly anything more abominable than a dangling false argument at the end of a function call. What does it mean? What would it change if it were true? Not only is the purpose of a selector argument difficult to remember, each selector argument combines many functions into one. Selector arguments are just a lazy way to avoid splitting a large function into several smaller functions.\nOf course, selectors needs not be boolean. They can be enums, integers, or any other type of argument that is used to select the behavior of the function. In general it is better to have many functions than to pass some code into a function to select the behavior."
    )
    val cleanCodeG16ObscuredIntent = createCleanCodeComment(
        "[G16] - Obscured Intent",
        "We want code to be as expressive as possible. Run-on expressions, Hungarian notation, and magic numbers all obscure the author’s intent.\nSmall and dense as this might appear, it’s also virtually impenetrable. It is worth taking the time to make the intent of our code visible to our readers."
    )
    val cleanCodeG17MisplacedResponsibility = createCleanCodeComment(
        "[G17] - Misplaced Responsibility",
        "One of the most important decisions a software developer can make is where to put code. For example, where should the PI constant go? Should it be in the Math class? Perhaps it belongs in the Trigonometry class? Or maybe in the Circle class?\nThe principle of least surprise comes into play here. Code should be placed where a reader would naturally expect it to be. The PI constant should go where the trig functions are declared. The OVERTIME_RATE constant should be declared in the HourlyPayCalculator class.\nSometimes we get “clever” about where to put certain functionality. We’ll put it in a function that’s convenient for us, but not necessarily intuitive to the reader. For example, perhaps we need to print a report with the total of hours that an employee worked. We could sum up those hours in the code that prints the report, or we could try to keep a running total in the code that accepts time cards.\nOne way to make this decision is to look at the names of the functions. Let’s say that our report module has a function named getTotalHours. Let’s also say that the module that accepts time cards has a saveTimeCard function. Which of these functions, by it’s name, implies that it calculates the total? The answer should be obvious.\nClearly, there are sometimes performance reasons why the total should be calculated as time cards are accepted rather than when the report is printed. That’s fine, but the names of the functions ought to reflect this. For example, there should be a computeRunningTotalOfHour function in the timecard module."
    )
    val cleanCodeG18InappropriateState = createCleanCodeComment(
        "[G18] - Inappropriate State",
        "Math.max(double a, double b) is a good static method. It does not operate on a single instance; indeed it would be silly to have to say new Math().max(a, b) or even a.max(b). All the data that max uses comes from its two arguments, and not from any “owning” object. More to the point, there is almost no chance that we’d want Math.max to be polymorphic.\nSometimes, however, we write static functions that should not be static.\nIn general you should prefer nonstatic methods to static methods. When in doubt, make the function nonstatic. If you really want a function to be static, make sure that there is no chance that you’ll want it to behave polymorphically."
    )
    val cleanCodeG19UseExplanatoryVariables = createCleanCodeComment(
        "[G19] - Use Explanatory Variables",
        "Kent Beck wrote about this in his great book Smalltalk Best Practice Patterns1 and again more recently in his equally great book Implementation Patterns2. One of the more powerful ways to make a program readable is to break the calculations up into intermediate values that are held in variables with meaningful names.\nIt is hard to overdo this. More explanatory variables are generally better than fewer. It is remarkable how an opaque module can suddenly become transparent simply by breaking the calculations up into well-named intermediate values."
    )
    val cleanCodeG20FunctionNamesShouldSayWhatTheyDo = createCleanCodeComment(
        "[G20] - Function Names Should Say What They Do",
        "If you have to look at the implementation (or documentation) of the function to know what it does, then you should work to find a better name or rearrange the functionality so that it can be placed in functions with better names."
    )
    val cleanCodeG21UnderstandTheAlgorithm = createCleanCodeComment(
        "[G21] - Understand the Algorithm",
        "Lots of very funny code is written because people don’t take the time to understand the algorithm. They get something to work by plugging in enough if statements and flags, without really stopping to consider what is really going on.\nProgramming if often an exploration. You think you know the right algorithm for something, but then you wind up fiddling with it, prodding and poking at it, until you get it to “work”. How do you know it “works”? Because it passes the test cases you can think of.\nThere is nothing wrong with this approach. Indeed, often it is the only way to get a function to do what you think it should. However, it is not sufficient to leave the quotation marks around the work “work”.\nBefore you consider yourself to be done with a function, make sure you understand how it works. It is not good enough that it passes all the tests. You must know1 that the solution is correct.\nOften the best way to gain this knowledge and understanding is to refactor the function into something that is so clean and expressive that it is obvious how it works."
    )
    val cleanCodeG22MakeLogicalDependenciesPhysical = createCleanCodeComment(
        "[G22] - Make Logical Dependencies Physical",
        "If one module depends upon another, that dependency should be physical, not just logical. The dependent module should not make assumptions (in other words, logical dependencies) about the module it depends upon. Rather it should ask that module for all the information it depends upon."
    )
    val cleanCodeG23PreferPolymorphismToIfElseOrSwitchCase = createCleanCodeComment(
        "[G23] - Prefer Polymorphism to If/Else or Switch/Case",
        "This might seem a strange suggestion given the topic of Chapter 6. After all, in that chapter I make the point that switch statements are probably appropriate in the parts of the system where adding new functions is more likely than adding new types.\nFirst, most people use switch statements because it’s the obvious brute force solution, not because it’s the right solution for the situation. So this heuristic is here to remind us to consider polymorphism before using a switch.\nSecond, the cases where functions are more volatile than types are relatively rare. So every switch statement should be suspect.\nI use the following “ONE SWITCH” rule: There may be no more than one switch statement for a given type of selection. The cases in that switch statement must create polymorphic objects that take the place of other switch statements in the rest of the system."
    )
    val cleanCodeG24FollowStandardConvention = createCleanCodeComment(
        "[G24] - Follow Standard Convention",
        "Every team should follow a coding standard based on common industry norms. This coding standard should specify things like where to declare instance variables; how to name classes, methods, and variables; where to put braces; and so on. The team should not need a document to describe these conventions because their code provides examples.\nEveryone on the team should follow these conventions. This means that each team member must be mature enough to realize that it doesn’t matter a white where you put your braces so long as you all agree on where to put them."
    )
    val cleanCodeG25ReplaceMagicNumbersWithNamedConstants = createCleanCodeComment(
        "[G25] - Replace Magic Numbers with Named Constants",
        "This is probably one of the oldest rules in software development. I remember reading it in the late sixties in introductory COBOL, FORTRAN, and PL/I manuals. In general it is a bad idea to have raw numbers in your code. you should hide them behind well-named constants.\nThe term “Magic Number” does not apply only to numbers. It applies to any token that has value that is not self-describing."
    )
    val cleanCodeG26BePrecise = createCleanCodeComment(
        "[G26] - Be Precise",
        "Expecting the first match to be the only match to a query is probably naive. Using floating point numbers to represent currency is almost criminal. Avoiding locks and/or transaction management because you don’t think concurrent update is likely is lazy at best. Declaring a variable to be an ArrayList when a List will do is overly constraining. Making all variables protected by default is not constraining enough.\nWhen you make a decision in your code, make sure you make it precisely. Know why you have made it and how you will deal with any exceptions. Don’t be lazy about the precision of your decisions. If you decide to call a function that might return null, make sure you check for null. If you query for what you think is the only record in the database, make sure your code checks to be sure there aren’t others. If you need to deal with currency, use integers1 and deal with rounding appropriately. If there is the possibility of concurrent update, make sure you implement some kind of locking mechanism.\nAmbiguities and imprecision in code are either a result of disagreements or laziness. In either case they should be eliminated."
    )
    val cleanCodeG27StructureOverConvention = createCleanCodeComment(
        "[G27] - Structure over Convention",
        "Enforce design decisions with structure over convention. Naming conventions are good, but they are inferior to structures that force compliance. For example, switch/cases with nicely named enumerations are inferior to base classes with abstract methods. No one is forced to implement the switch/case statement the same way each time; but the base classes do enforce that concrete classes have all abstract methods implemented."
    )
    val cleanCodeG28EncapsulateConditionals = createCleanCodeComment(
        "[G28] - Encapsulate Conditionals",
        "Boolean logic is hard enough to understand without having to see it in the context of an if or while statement. Extract functions that explain the intent of the conditional."
    )
    val cleanCodeG29AvoidNegativeConditionals = createCleanCodeComment(
        "[G29] - Avoid Negative Conditionals",
        "Negatives are just a bit harder to understand than positives. So, when possible, conditionals should be expressed as positives."
    )
    val cleanCodeG30FunctionsShouldDoOneThing = createCleanCodeComment(
        "[G30] - Functions Should Do One Thing",
        "It is often tempting to create functions that have multiple sections that perform a series of operations. Functions of this kind do more than one thing, and should be converted into many smaller functions, each of which does one thing."
    )
    val cleanCodeG31HiddenTemporalCoupling = createCleanCodeComment(
        "[G31] - Hidden Temporal Coupling",
        "Temporal couplings are often necessary, but you should not hide the coupling. Structure the arguments of your functions such that the order in which they should be called is obvious.\nEach function produces a result that the next function needs, so there is no reasonable way to call them out of order.\nYou might complain that this increases the complexity of the functions, and you’d be right. But that extra syntactic complexity exposes the true temporal complexity of the situation."
    )
    val cleanCodeG32DontBeArbitrary = createCleanCodeComment(
        "[G32] - Don't Be Arbitrary",
        "Have a reason for the way you structure your code, and make sure that reason is communicated by the structure of the code. If a structure appears consistently throughout the system, others will use it and preserve the convention.\nPublic classes that are not utilities of some other class should not be scoped inside another class. The convention is to make them public att the top level of their package."
    )
    val cleanCodeG33EncapsulateBoundaryConditions = createCleanCodeComment(
        "[G33] - Encapsulate Boundary Conditions",
        "Boundary conditions are hard to keep track of. Put the processing of them in one place. Don’t let them leak all over the code. We don’t want swarms of +1s and -1s scatter hither and yon."
    )
    val cleanCodeG34FunctionsShouldDescendOnlyOneLevelOfAbstraction = createCleanCodeComment(
        "[G34] - Functions Should Descend Only One Level of Abstraction",
        "The statements within a function should all be written at the same level of abstraction, which should be one level below the operation described by the name of the function. This may be the hardest of these heuristics to interpret and follow. Though the idea is plain enough, humans are just far too good at seamlessly mixing levels of abstraction.\nSeparating levels of abstraction is one of the most important functions of refactoring, and it’s one of the hardest to do well."
    )
    val cleanCodeG35KeepConfigurableDataAtHighLevels = createCleanCodeComment(
        "[G35] - Keep Configurable Data at High Levels",
        "If you have a constant such as a default or configuration value that is known and expected at a high level of abstraction, do not bury it in a low-level function. Expose it as an argument to that low-level function called from the high-level function.\nThe configuration constants reside at a very high level and are easy to change. They get passed down to the rest of the application. The lower levels of the application do not own the values of these constants."
    )
    val cleanCodeG36AvoidTransitiveNavigation = createCleanCodeComment(
        "[G36] - Avoid Transitive Navigation",
        "In general we don’t want a single module to know much about its collaborators. More specifically, if A collaborates with B, and B collaborates with C, we don’t want modules that use A to know about C.\nThis is sometimes called the Law of Demeter. The Pragmatic Programmers call it “Writing Shy Code.”[^12] In either case it comes down to making sure that modules know only about their immediate collaborators and do not know the navigation map of the whole system.\nIf many modules used some form of the statement a.getB().getC(), then it would be difficult to change the design and architecture to interpose a Q between B and C. You’d have to find every instance of a.getB().getC() and convert it to a.getB().getQ().getC(). This is how architectures become rigid. Too many modules know too much about the architecture.\nRather we want our immediate collaborators to offer all the services we need. We should not have to roam through the object graph of the system, hunting for the method we want to call."
    )
    val cleanCodeJ01AvoidLongImportListsByUsingWildcards = createCleanCodeComment(
        "[J01] - Avoid Long Import Lists by Using Wildcards",
        "Long lists of imports are daunting to the reader. We don’t want to clutter up the tops of our modules with 80 lines of imports. Rather we want the imports to be a concise statements about which packages we collaborate with.\nSpecific imports are hard dependencies, whereas wildcard imports are not. If you specifically import a class, then that class must exist. But if you import a package with a wildcard, no particular classes need to exist. The import statement simply adds the package to the search path when hunting for names. So no true dependency is created by such imports, and they therefore serve to keep our modules less coupled.\nThere are times when the long list of specific imports can be useful. For example, if you are dealing with legacy code and you want to find out what classes you need to build mocks and stubs for, you can walk down the list of specific imports to find out the true qualified names of all those classes and then put the appropriate stubs in place. However, this use for specific imports is very rare. Furthermore, most modern IDEs will allow you to convert the wildcarded imports to a list of specific imports with a single command. So even in the legacy case it’s better to import wildcards.\nWildcard imports can sometimes cause name conflicts and ambiguities. Two classes with the same name, but in different packages, will need to be specifically imported, or at least specifically qualified when used. This can be a nuisance but is rare enough that using wildcard imports is still generally better than specific imports."
    )
    val cleanCodeJ02DontInheritConstants = createCleanCodeComment(
        "[J02] - Don't Inherit Constants",
        "I have seen this several times it always makes me grimace. A programmer puts some constants in an interface and then gains access to those constants by inheriting that interface.\nThis is a hideous practice! The constants are hidden at the top of the inheritance hierarchy. Ick! Don’t use inheritance as a way to cheat the scoping rules of the language. Use a static import instead."
    )
    val cleanCodeJ03ConstantsVersusEnums = createCleanCodeComment(
        "[J03] - Constants versus Enums",
        "Now that enums have been added to the language (Java 5), use them! Don’t keep using the old trick of public static final ints. The meaning of ints can get lost. The meaning of enum cannot, because they belong to an enumeration that is named.\nWhat’s more, study the syntax for enums carefully. They can have methods and fields. This makes them more powerful tools that allow much more expression and flexibility than ints."
    )
    val cleanCodeN01ChooseDescriptiveNames = createCleanCodeComment(
        "[N01] - Choose Descriptive Names",
        "Don’t be too quick to choose a name. ake sure the name is descriptive. Remember that meanings tend to drift as software evolves, so frequently reevaluate the appropriateness of the names you choose.\nThis is not just a “feel-good” recommendation. Names in software are 90 percent of what make software readable. You need to take the time to choose them wisely and keep them relevant. Names are too important to treat carelessly.\nThe power of carefully chosen names is that they overload the structure of the code with description. That overloading sets the readers’ expectations about what the other functions in the module do."
    )
    val cleanCodeN02ChooseNamesAtTheAppropriateLevelOfAbstraction = createCleanCodeComment(
        "[N02] - Choose Names at the Appropriate Level of Abstraction",
        "Don’t pick names that communicate implementation; choose names that reflect the level of abstraction of the class or function you are working in. This is hard to do. Again, people are just too good at mixing levels of abstractions. Each time you make a pass over your code, you will likely find some variable that is named at too low a level. You should take the opportunity to change those names when you find them. Making code readable requires a dedication to continuous improvement."
    )
    val cleanCodeN03UseStandardNomenclatureWherePossible = createCleanCodeComment(
        "[N03] - Use Standard Nomenclature Where Possible",
        "Names are easier to understand if they are based on existing convention or usage. For example, if you are using the Decorator pattern, you should use the word Decorator in the names of the decorating class. For example, AutoHangupModemDecorator might be the name of a class that decorate Modem with the ability to automatically hang up at the end of a session.\nPatterns are just one kind of standard. In Java, for example, functions that convert objects to String representations are often named toString. It is better to follow conventions like these than to invent your own.\nTeams will often invent their own standard system of names for a particular project. Eric Evans refers to this as ubiquitous language for the project.1 Your code should use the terms from this language extensively. In short, the more you can use names that are overloaded with special meanings that are relevant to your project, the easier it will be for readers to know what your code is talking about."
    )
    val cleanCodeN04UnambiguousNames = createCleanCodeComment(
        "[N04] - Unambiguous Names",
        "Choose names that make the working of the function or variable unambiguous."
    )
    val cleanCodeN05UseLongNamesForLongScopes = createCleanCodeComment(
        "[N05] - Use Long Names for Long Scopes",
        "The length of a name should be related to the length of the scope. You can use very short variable names for tiny scopes, but for big scopes you should use longer names.\nVariables and functions with short names lose their meaning over long distances. So the longer the scope of the name, the longer and more precise the name should be."
    )
    val cleanCodeN06AvoidEncodings = createCleanCodeComment(
        "[N06] - Avoid Encodings",
        "Names should not be encoded with type of scope information. Prefixes such as m_ or f are useless in today’s environments. Also project and/or subsystem encodings such as vis_ (for visual imaging system) are distracting and redundant. Again, today’s environments provide all that information without having to mangle the names. Keep your names free of Hungarian pollution."
    )
    val cleanCodeN07NamesShouldDescribeSideEffects = createCleanCodeComment(
        "[N07] - Names Should Describe Side-Effects",
        "Names should describe everything that a function, variable, or class is or does. Don’t hide the effects with a name. Do’t use a simple verb to describe a function that does more than just a simple action."
    )
    val cleanCodeT01InsufficientTests = createCleanCodeComment(
        "[T01] - Insufficient Tests",
        "How many tests should be in a test suite? Unfortunately, the metric many programmers use is “That seems like enough”. A test suite should test everything that could possible break. The tests are insufficient so long as there are conditions that have not been explored by the tests or calculation that have not been validated."
    )
    val cleanCodeT02UseACoverageTool = createCleanCodeComment(
        "[T02] - Use a Coverage Tool!",
        "Coverage tools report gaps in your testing strategy. They make it easy to find modules, classes, and functions that are insufficiently tested. Most IDEs give you a visual indication, marking lines that are covered in green and those that are uncovered in red. This makes it quick and easy to find if or catch statements whose bodies haven’t been checked."
    )
    val cleanCodeT03DontSkipTrivialTests = createCleanCodeComment(
        "[T03] - Don't Skip Trivial Tests",
        "They are easy to write and their documentary value is higher than the code to produce them."
    )
    val cleanCodeT04AnIgnoredTestIsAQuestionAboutAnAmbiguity = createCleanCodeComment(
        "[T04] - An Ignored Test Is a Question about an Ambiguity",
        "Sometimes we are uncertain about a behavioral detail because the requirements are unclear. We can express our question about the requirements as a test that is commented out, or as a test that annotated with @Ignore. Which you choose depends upon whether the ambiguity is about something that would compile or not."
    )
    val cleanCodeT05TestBoundaryConditions = createCleanCodeComment(
        "[T05] - Test Boundary Conditions",
        "Take special care to test boundary conditions. We often get the middle of an algorithm right but misjudge the boundaries."
    )
    val cleanCodeT06ExhaustivelyTestNearBugs = createCleanCodeComment(
        "[T06] - Exhaustively Test Near Bugs",
        "Bugs tend to congregate. When you find a bug in a function, it is wise to do an exhaustive test of that function. You’ll probably find that the bug was not alone."
    )
    val cleanCodeT07PatternsOfFailureAreRevealing = createCleanCodeComment(
        "[T07] - Patterns of Failure Are Revealing",
        "Sometimes you can diagnose a problem by finding patterns in the way the test cases fail. This is another argument for making the test cases as complete as possible. Complete test cases, ordered in a reasonable way, expose patterns. As a simple example, suppose you noticed that all tests with an input larger than five characters failed? or what if any test that passed a negative number into a second argument of a function failed? Sometimes just seeing the pattern of red and green on the test report is enough to spark the “Aha!” that leads to the solution. Look page at page 267 to see an interesting example of this in the SerialDate example."
    )
    val cleanCodeT08TestCoveragePatternsCanBeRevealing = createCleanCodeComment(
        "[T08] - Test Coverage Patterns Can Be Revealing",
        "Looking at the code that is or is not executed by the passing tests gives clues to why the failing tests fail."
    )
    val cleanCodeT09TestsShouldBeFast = createCleanCodeComment(
        "[T09] - Tests Should Be Fast",
        "A slow test is a test that won’t get run. When things get tight, it’s the slow tests that will be dropped from the suite. So do what you must to keep your tests fast."
    )
    val solid1SingleResponsibilityPrinciple = createSolidPrinciplesComment(
        "[SOLID1] - Single Responsibility Principle (SRP)",
        "A class should have one and only one reason to change, meaning that a class should have only one job."
    )
    val solid2OpenClosedPrinciple = createSolidPrinciplesComment(
        "[SOLID2] - Open-Closed Principle (OCP)",
        "Objects or entities should be open for extension, but closed for modification."
    )
    val solid3LiskovSubstitutionPrinciple = createSolidPrinciplesComment(
        "[SOLID3] - Liskov Substitution Principle (LSP)",
        "Let q(x) be a property provable about objects of x of type T. Then q(y) should be provable for objects y of type S where S is a subtype of T."
    )
    val solid4InterfaceSegregationPrinciple = createSolidPrinciplesComment(
        "[SOLID4] - Interface Segregation Principle (ISP)",
        "A client should never be forced to implement an interface that it doesn't use or clients shouldn't be forced to depend on methods they do not use."
    )
    val solid5DependencyInversionPrinciple = createSolidPrinciplesComment(
        "[SOLID5] - Dependency Inversion Principle (DIP)",
        "Entities must depend on abstractions not on concretions. It states that the high level module must not depend on the low level module, but they should depend on abstractions."
    )
}

fun createCleanCodeComment(label: String, description: String): ReviewCommentDetails {
    return ReviewCommentDetails(label, description, mutableListOf(CLEAN_CODE))
}

fun createSolidPrinciplesComment(label: String, description: String): ReviewCommentDetails {
    return ReviewCommentDetails(label, description, mutableListOf(ARCHITECTURE))
}

fun getAllPresetReviewCommentDetails(): Array<ReviewCommentDetails> {
    return PresetReviewCommentDetails::class.declaredMemberProperties.map(fun(x): ReviewCommentDetails {
        return x.get(PresetReviewCommentDetails()) as ReviewCommentDetails
    }).toTypedArray()
}
