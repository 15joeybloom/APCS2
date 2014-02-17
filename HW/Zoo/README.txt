Checkpoint 1
Ant is the main class/tester for this first checkpoint. It tests its own scoring algorithm used for evaluating the attractiveness or repulsiveness of Entities as well as testing the Rock class.

Checkpoint 2
Action has the main method/tester for this second checkpoint. It tests the scoring and acting of AbstractAnimal by constructing an Ant, placing it in a zoo with two rocks. This is similar to gridworld, but the Entities are not constricted to grid squares; their coordinates are double-precision. The way animals act is also similar to gridworld act method, especially for Critter, with the several helper methods.


Links that really helped me:
	Resizing images: http://msmshariq.blogspot.com/2011/07/scaling-transparent-images-in-java.html

FINAL SUBMISSION

To launch the project, double click either AntEater.jar or Ants.jar.



Highlights:

PriorityQueue used to determine which Entity to act upon

Simple AI algorithms caused emergent behaviors, shown in Zoo/screenshots. AntEaters (pac-man looking thing) tends to hang out by the Spawner (large Ant
with swirly/spiral thing) but sometimes chases Ants away from the Spawner and then returns. Ants tend to hang out in groups near large, colorful Entities like
the huge magenta Rock.



Bugs:
	Ants sometimes stop moving completely, which is not a desired behavior.
	Spawner might spawn Ants too often.

	