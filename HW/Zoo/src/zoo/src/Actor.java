package zoo.src;

import java.util.*;

/**
 <p>
 @author Joey Bloom
 */
public interface Actor extends Entity
{
    /**
     Causes this animal to evaluate its surroundings, determine an appropriate
     Action, and begin that Action.
     <p>
     * @param entities
     * @param changes This set contains Entities to add or remove from the zoo.
            If an Entity in changes are already in the zoo, then it is to
            be removed. If an Entity in changes is not in the zoo, then it is
            to be added.
     */
    void act(Set<Entity> entities, Set<Entity> changes);
}
