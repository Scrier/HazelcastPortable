package io.scrier.hazelcast.portable;

import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
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
        writeMap.put("testVer1ToVer1", expected);
        io.scrier.hazelcast.portable.ver1.PortableImpl actual = readMap.get("testVer1ToVer1");

        System.out.println(expected);
        System.out.println(actual);

        assertThat(expected, is(actual));
    }

    @Test
    public void testVer1ToVer1Rand() {
        IMap<String, io.scrier.hazelcast.portable.ver1.PortableImpl> writeMap = ver1.getMap("Ver1ToVer1Rand");
        IMap<String, io.scrier.hazelcast.portable.ver1.PortableImpl> readMap = ver1.getMap("Ver1ToVer1Rand");

        io.scrier.hazelcast.portable.ver1.PortableImpl expected = io.scrier.hazelcast.portable.ver1.PortableImpl.generate();
        writeMap.put("testVer1ToVer1Rand", expected);
        io.scrier.hazelcast.portable.ver1.PortableImpl actual = readMap.get("testVer1ToVer1Rand");

        System.out.println(expected);
        System.out.println(actual);

        assertThat(expected, is(actual));
    }

    @Test
    public void testVer2ToVer2() {
        IMap<String, io.scrier.hazelcast.portable.ver2.PortableImpl> writeMap = ver2.getMap("Ver2ToVer2");
        IMap<String, io.scrier.hazelcast.portable.ver2.PortableImpl> readMap = ver2.getMap("Ver2ToVer2");

        io.scrier.hazelcast.portable.ver2.PortableImpl expected = io.scrier.hazelcast.portable.ver2.PortableImpl.generate(0x0000001F);
        writeMap.put("testVer2ToVer2", expected);
        io.scrier.hazelcast.portable.ver2.PortableImpl actual = readMap.get("testVer2ToVer2");

        System.out.println(expected);
        System.out.println(actual);

        assertThat(expected, is(actual));
    }

    @Test
    public void testVer2ToVer2Rand() {
        IMap<String, io.scrier.hazelcast.portable.ver2.PortableImpl> writeMap = ver2.getMap("Ver2ToVer2Rand");
        IMap<String, io.scrier.hazelcast.portable.ver2.PortableImpl> readMap = ver2.getMap("Ver2ToVer2Rand");

        io.scrier.hazelcast.portable.ver2.PortableImpl expected = io.scrier.hazelcast.portable.ver2.PortableImpl.generate();
        writeMap.put("testVer2ToVer2Rand", expected);
        io.scrier.hazelcast.portable.ver2.PortableImpl actual = readMap.get("testVer2ToVer2Rand");

        System.out.println(expected);
        System.out.println(actual);

        assertThat(expected, is(actual));
    }

    @Test
    public void testVer3ToVer3() {
        IMap<String, io.scrier.hazelcast.portable.ver3.PortableImpl> writeMap = ver3.getMap("Ver3ToVer3");
        IMap<String, io.scrier.hazelcast.portable.ver3.PortableImpl> readMap = ver3.getMap("Ver3ToVer3");

        io.scrier.hazelcast.portable.ver3.PortableImpl expected = io.scrier.hazelcast.portable.ver3.PortableImpl.generate(0x0000000F);
        writeMap.put("testVer3ToVer3", expected);
        io.scrier.hazelcast.portable.ver3.PortableImpl actual = readMap.get("testVer3ToVer3");

        System.out.println(expected);
        System.out.println(actual);

        assertThat(expected, is(actual));
    }

    @Test
    public void testVer3ToVer3Rand() {
        IMap<String, io.scrier.hazelcast.portable.ver3.PortableImpl> writeMap = ver3.getMap("Ver3ToVer3Rand");
        IMap<String, io.scrier.hazelcast.portable.ver3.PortableImpl> readMap = ver3.getMap("Ver3ToVer3Rand");

        io.scrier.hazelcast.portable.ver3.PortableImpl expected = io.scrier.hazelcast.portable.ver3.PortableImpl.generate();
        writeMap.put("testVer3ToVer3Rand", expected);
        io.scrier.hazelcast.portable.ver3.PortableImpl actual = readMap.get("testVer3ToVer3Rand");

        System.out.println(expected);
        System.out.println(actual);

        assertThat(expected, is(actual));
    }

    @Test
    public void testVer1ToVer2() {
        IMap<String, io.scrier.hazelcast.portable.ver1.PortableImpl> writeMap = ver1.getMap("Ver1ToVer2");
        IMap<String, io.scrier.hazelcast.portable.ver2.PortableImpl> readMap = ver2.getMap("Ver1ToVer2");

        io.scrier.hazelcast.portable.ver1.PortableImpl expected = io.scrier.hazelcast.portable.ver1.PortableImpl.generate(0x0000000F);

        System.out.println(expected);
        writeMap.put("testVer1ToVer2", expected);
        io.scrier.hazelcast.portable.ver2.PortableImpl actual = readMap.get("testVer1ToVer2");

        System.out.println(expected);
        System.out.println(actual);

        assertThat(actual.getDoubleValue(), is(expected.getDoubleValue()));
        assertThat(actual.getIntValue(), is(expected.getIntValue()));
        assertThat(actual.getLongValue(), is(expected.getLongValue()));
        assertThat(actual.getStringValue(), is(expected.getStringValue()));
    }

    @Test
    public void testVer1ToVer2Rand() {
        IMap<String, io.scrier.hazelcast.portable.ver1.PortableImpl> writeMap = ver1.getMap("Ver1ToVer2Rand");
        IMap<String, io.scrier.hazelcast.portable.ver2.PortableImpl> readMap = ver2.getMap("Ver1ToVer2Rand");

        io.scrier.hazelcast.portable.ver1.PortableImpl expected = io.scrier.hazelcast.portable.ver1.PortableImpl.generate();

        System.out.println(expected);
        writeMap.put("testVer1ToVer2Rand", expected);
        io.scrier.hazelcast.portable.ver2.PortableImpl actual = readMap.get("testVer1ToVer2Rand");

        System.out.println(expected);
        System.out.println(actual);

        assertThat(actual.getDoubleValue(), is(expected.getDoubleValue()));
        assertThat(actual.getIntValue(), is(expected.getIntValue()));
        assertThat(actual.getLongValue(), is(expected.getLongValue()));
        assertThat(actual.getStringValue(), is(expected.getStringValue()));
    }

    @Test
    public void testVer2ToVer3() {
        IMap<String, io.scrier.hazelcast.portable.ver2.PortableImpl> writeMap = ver2.getMap("Ver2ToVer3");
        IMap<String, io.scrier.hazelcast.portable.ver3.PortableImpl> readMap = ver3.getMap("Ver2ToVer3");

        io.scrier.hazelcast.portable.ver2.PortableImpl expected = io.scrier.hazelcast.portable.ver2.PortableImpl.generate(0x0000001F);

        System.out.println(expected);
        writeMap.put("testVer2ToVer3", expected);
        io.scrier.hazelcast.portable.ver3.PortableImpl actual = readMap.get("testVer2ToVer3");

        System.out.println(expected);
        System.out.println(actual);

        assertThat(actual.getDoubleValue(), is(expected.getDoubleValue()));
        assertThat(actual.getIntValue(), is(expected.getIntValue()));
        assertThat(actual.getLongValue(), is(expected.getLongValue()));
        assertThat(actual.getShortValue(), is(expected.getShortValue()));
    }

    @Test
    public void testVer2ToVer3Rand() {
        IMap<String, io.scrier.hazelcast.portable.ver2.PortableImpl> writeMap = ver2.getMap("Ver2ToVer3Rand");
        IMap<String, io.scrier.hazelcast.portable.ver3.PortableImpl> readMap = ver3.getMap("Ver2ToVer3Rand");

        io.scrier.hazelcast.portable.ver2.PortableImpl expected = io.scrier.hazelcast.portable.ver2.PortableImpl.generate();

        System.out.println(expected);
        writeMap.put("testVer2ToVer3Rand", expected);
        io.scrier.hazelcast.portable.ver3.PortableImpl actual = readMap.get("testVer2ToVer3Rand");

        System.out.println(expected);
        System.out.println(actual);

        assertThat(actual.getDoubleValue(), is(expected.getDoubleValue()));
        assertThat(actual.getIntValue(), is(expected.getIntValue()));
        assertThat(actual.getLongValue(), is(expected.getLongValue()));
        assertThat(actual.getShortValue(), is(expected.getShortValue()));
    }

    @Test
    @Ignore("Only Run Manual")
    public void testAll() {
        for( int i = 0; i < 100; i++ ) {
            testVer1ToVer1();
            testVer1ToVer1Rand();
            testVer1ToVer2();
            testVer1ToVer2Rand();
            testVer2ToVer2();
            testVer2ToVer2Rand();
            testVer3ToVer3();
            testVer3ToVer3Rand();
            testVer2ToVer3();
            testVer2ToVer3Rand();
        }
    }

}
