# The Rocket Scientist Problem – CA4006 Assessment 1

You have been hired by SpaceCorp to build a robot space exploration mission controller for at least
10 simultaneous missions. Each mission consists of boost stage, an interplanetary transit stage, an
entry/landing stage and an exploration (rover) stage. Depending on the mission target, each mission
must be allocated variable supplies of fuel, thrusters, instruments, control systems and powerplants.
The mission destination can be approximated as a function of the fuel load for the mission (ie more
fuel implies a mission to further locations in the solar system).


The transit and exploration mission stages takes variable amounts of time to execute (in months),
boost and landing are effectively instant events. Each stage has at least a 10% chance of failing. 25%
of failures can be recovered from by sending a software upgrade which takes a variable number of
days to develop and is a variable size in MB. There are three types of deep space communications
networks (2MB/sec with 80% availability, 2KB/sec with 90% availability and 20bits/sec with 99.9%
availability).When on a mission it is necessary for all mission components to transmit reports
(telemetry ) on progress and instruments send data on a regular basis, but this is limited by
bandwidth and subject to increasing delays as the mission travels further away from Earth. 30% of
reports require a command response and the mission is paused until that command is received.
Software upgrades must be transmitted from the mission controller. A variable burst of reports and
commands are sent at the transition between mission stages. There are a variable number of types
of commands and reports for each mission. Reports can be telemetry (100-10k bytes, frequent) or
data (100k-100MB, periodic)


Each mission can be represented using threads. You must implement the methods called by the
mission controller to construct the mission vehicles from components, move missions along their
stages, check for failures, send reports, send instructions and software updates. It is important to
represent the communications networks (queues) too. A vanilla option would be to assume the
following:

The communication networks for a mission are a shared resource used by all mission
components, but each mission has its own network.
- Network availability can be checked before a message is to be sent and if a network is
available then it can be used to transmit the full message.

- The mission controller is a shared resource used for all missions.

- Time can simulated by allowing a fixed ratio of wall clock time to mission time eg 1 sec : 1
month.
- When waiting a mission 'sleeps'.

These specifications are for the vanilla option of the implementation. Making use of java large-scale
thread management support is essential to secure a high grade. Any form of creativity that you feel
like putting in that will add interest to my marking of the project (multiple mission controllers, just in
time supplies, Nice GUIs, reporting on the number of threads executing/number of jobs being
performed, random events such as solar flares etc) is encouraged and marks awarded for the project
will reflect this.

Hint: One Approach to the Problem Might be the following:
The program will contain four classes “mission”, “component”, “controller” and “network”. Each
"mission" should be generated with a random start time, components list, destination, network and

have a unique ID for identification purposes. Each component should have a random report rate
and size for the mission.
In order to compile your java code, I should just be able to do so with java compiler with `javac <filename>` 
and after it gets compiled execute it with `java <filename(executable)>`. 
Please do not rely on my using an IDE for the execution of your code.

**Output file:**
The format for an output file should be something like following: 
`Mission Component # with (Thread ID) # makes request to network # at time # for message #.`
There should be as many lines printed as are the number of requests. You should also generate an
output file output.dat to store your output.
Note: You must assume that you the starting time for the first request is 0.
Deliverables and instructions for submission:

- There will be a short intermediate presentation phase where you present your approach to
the class. Every group must prepare 1 slide on their architecture/approach for the lecture
on the 2nd March (ungraded).
- The assignment is a two person project, worth 15% of the overall module mark and has to
be done in Java. Basic Java threads and locks is a minimal solution.
- Please also write a short (5 page max) design documentation which will be evaluated. The
report should describe: 
    1. Design overview, component diagram, 
    2. Concurrency problems identified and solutions eg fairness, prevention of starvation etc. , 
    3. How to compile and run the code 
    4. Max 1 page each on personal reflections: what did you learn? how does it relate to course material? 
        What work did you contribute? What would you do different next time?
- Please submit all your source files, documentation through Loop with a completed plagiarism declaration form which is signed by both of you.
- Deadline for submission is the **Friday 12th March at 5.00pm**. Penalties will apply for late submissions.
- All projects will have to create a demo video by the 16th March. Please create a screencast video (max 5mins) of your project in operation (a demo) and also 
address these questions in the video via voice-over and screencast: 
    1. What did you learn in the project? (1 answer per person) 
    2. Walk through of the toughest bit of code you wrote for the project (1 for each
    person). A sample of project teams may be called to a zoom interview to answer questions on your system.