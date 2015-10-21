package linkedlist;

import linkedlist.cycle.SimpleLinkedList;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SimpleLinkedListTest {

    @Test
    public void hasLoopOfEmptyList() {
        SimpleLinkedList<String> sl = new SimpleLinkedList<String>();
        boolean hasLoop = sl.hasLoop();
        assertThat(hasLoop, is(Boolean.FALSE));
    }

    @Test
    public void hasLoopOfSingleNode() {
        //// A list has a node, without a loop
        SimpleLinkedList<String> sl = new SimpleLinkedList<String>();
        sl.add ("foo");
        boolean hasLoop = sl.hasLoop();
        assertThat(hasLoop, is(Boolean.FALSE));
    }

    @Test
    public void hasLoopOfSingleNode2() {
        //// A list has a node, with a loop
        SimpleLinkedList<String> sl = new SimpleLinkedList<String>();
        sl.add ("foo");
        sl.connectTo(0);
        boolean hasLoop = sl.hasLoop();
        assertThat(hasLoop, is(Boolean.TRUE));
    }

    @Test
    public void hasLoopOfMultipleNodes() {
        //// A list has multiple nodes, without a loop
        SimpleLinkedList<String> sl = new SimpleLinkedList<String>();
        sl.add ("foo");
        sl.add (1, "bar");
        sl.add ("baz");
        sl.add (0, "hello");
        sl.add (1, "world");
        boolean hasLoop = sl.hasLoop();
        assertThat(hasLoop, is(Boolean.FALSE));
    }

    @Test
    public void hasLoopOfMultipleNodes2() {
        //// A list has multiple nodes, with a loop
        SimpleLinkedList<String> sl = new SimpleLinkedList<String>();
        sl.add ("foo");
        sl.add (1, "bar");
        sl.add ("baz");
        sl.add (0, "hello");
        sl.add (1, "world");
        sl.connectTo(2);

        boolean hasLoop = sl.hasLoop();
        assertThat(hasLoop, is(Boolean.TRUE));
    }
}
