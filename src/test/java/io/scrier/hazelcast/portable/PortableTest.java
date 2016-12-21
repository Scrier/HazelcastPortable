package io.scrier.hazelcast.portable;

import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * TBD
 */
public class PortableTest {

    HazelcastInstance ver1;
    HazelcastInstance ver2;
    HazelcastInstance ver3;

    ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setup() {
        Config configVer1 = new Config();
        Config configVer2 = new Config();
        Config configVer3 = new Config();
        configVer1 = io.scrier.hazelcast.portable.ver1.PortableFactoryImpl.populate(configVer1);
        configVer2 = io.scrier.hazelcast.portable.ver2.PortableFactoryImpl.populate(configVer2);
        configVer3 = io.scrier.hazelcast.portable.ver3.PortableFactoryImpl.populate(configVer3);
        ver1 = Hazelcast.newHazelcastInstance(configVer1);
        ver2 = Hazelcast.newHazelcastInstance(configVer2);
        ver3 = Hazelcast.newHazelcastInstance(configVer3);
    }

    @After
    public void tearDown() {
        ver1.shutdown();
        ver2.shutdown();
        ver3.shutdown();
    }

    @Test
    public void testVer1ToVer1() {
        IMap<String, io.scrier.hazelcast.portable.ver1.PortableImpl> writeMap = ver1.getMap("Ver1ToVer1");
        IMap<String, io.scrier.hazelcast.portable.ver1.PortableImpl> readMap = ver1.getMap("Ver1ToVer1");

        io.scrier.hazelcast.portable.ver1.PortableImpl expected = io.scrier.hazelcast.portable.ver1.PortableImpl.generate(0x0000000F);
        writeMap.put("test", expected);
        io.scrier.hazelcast.portable.ver1.PortableImpl actual = readMap.get("test");

        System.out.println(expected);
        System.out.println(actual);

        assertThat(expected, is(actual));
    }

    @Test
    public void testVer2ToVer2() {
        IMap<String, io.scrier.hazelcast.portable.ver2.PortableImpl> writeMap = ver2.getMap("Ver2ToVer2");
        IMap<String, io.scrier.hazelcast.portable.ver2.PortableImpl> readMap = ver2.getMap("Ver2ToVer2");

        io.scrier.hazelcast.portable.ver2.PortableImpl expected = io.scrier.hazelcast.portable.ver2.PortableImpl.generate(0x0000001F);
        writeMap.put("test", expected);
        io.scrier.hazelcast.portable.ver2.PortableImpl actual = readMap.get("test");

        System.out.println(expected);
        System.out.println(actual);

        assertThat(expected, is(actual));
    }

    @Test
    public void testVer3ToVer3() {
        IMap<String, io.scrier.hazelcast.portable.ver3.PortableImpl> writeMap = ver3.getMap("Ver3ToVer3");
        IMap<String, io.scrier.hazelcast.portable.ver3.PortableImpl> readMap = ver3.getMap("Ver3ToVer3");

        io.scrier.hazelcast.portable.ver3.PortableImpl expected = io.scrier.hazelcast.portable.ver3.PortableImpl.generate(0x0000000F);
        writeMap.put("test", expected);
        io.scrier.hazelcast.portable.ver3.PortableImpl actual = readMap.get("test");

        System.out.println(expected);
        System.out.println(actual);

        assertThat(expected, is(actual));
    }

    @Test
    public void testVer1ToVer2() {
        IMap<String, io.scrier.hazelcast.portable.ver1.PortableImpl> writeMap = ver1.getMap("Ver1ToVer2");
        IMap<String, io.scrier.hazelcast.portable.ver2.PortableImpl> readMap = ver2.getMap("Ver1ToVer2");

        io.scrier.hazelcast.portable.ver1.PortableImpl expected = io.scrier.hazelcast.portable.ver1.PortableImpl.generate();
        writeMap.put("test", expected);
        io.scrier.hazelcast.portable.ver2.PortableImpl actual = readMap.get("test");

        System.out.println(expected);
        System.out.println(actual);

        assertThat(actual.getDoubleValue(), is(expected.getDoubleValue()));
        assertThat(actual.getIntValue(), is(expected.getIntValue()));
        assertThat(actual.getLongValue(), is(expected.getLongValue()));
        assertThat(actual.getStringValue(), is(expected.getStringValue()));
    }

}
