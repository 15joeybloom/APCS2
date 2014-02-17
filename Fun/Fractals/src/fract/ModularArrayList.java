package fract;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Joey Bloom
 */
public class ModularArrayList<T> extends ArrayList<T>
{
    public ModularArrayList(Collection<T> c)
    {
        for(T el : c)
        {
            add(el);
        }
    }

    @Override
    public T get(int i)
    {
        return super.get(i%size());
    }

    @Override
    public T set(int i, T el)
    {
        return super.set(i%size(),el);
    }
}
