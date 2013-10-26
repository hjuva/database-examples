package no.bekk.database;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class TestSet<E> implements Set<E> {

	private final boolean a = false;
	public final boolean b = false;
	private final static boolean c = false;

	@Override
	public int size() {
		return 0;
	}

	@Override
	public boolean isEmpty() {
		return false;
	}

	@Override
	public boolean contains(final Object o) {
		return false;
	}

	@Override
	public Iterator<E> iterator() {
		return null;
	}

	@Override
	public Object[] toArray() {
		return null;
	}

	@Override
	public <T> T[] toArray(final T[] a) {
		return null;
	}

	@Override
	public boolean add(final E e) {
		return false;
	}

	@Override
	public boolean remove(final Object o) {
		return false;
	}

	@Override
	public boolean containsAll(final Collection<?> c) {
		return false;
	}

	@Override
	public boolean addAll(final Collection<? extends E> c) {
		return false;
	}

	@Override
	public boolean retainAll(final Collection<?> c) {
		return false;
	}

	@Override
	public boolean removeAll(final Collection<?> c) {
		return false;
	}

	@Override
	public void clear() {
	}

}
