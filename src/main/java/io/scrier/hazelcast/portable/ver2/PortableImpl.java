package io.scrier.hazelcast.portable.ver2;

import com.google.common.base.MoreObjects;
import com.hazelcast.nio.serialization.Portable;
import com.hazelcast.nio.serialization.PortableReader;
import com.hazelcast.nio.serialization.PortableWriter;

import java.io.IOException;
import java.util.BitSet;
import java.util.Objects;
import java.util.Random;

/**
 * TBD
 */
public class PortableImpl implements Portable {

    public static final String BITSET = "bitSet";
    public static final String INT_VALUE = "intValue";
    public static final String STRING_VALUE = "stringValue";
    public static final String DOUBLE_VALUE = "doubleValue";
    public static final String LONG_VALUE = "longValue";
    public static final String SHORT_VALUE = "shortValue";

    static Random random = new Random();

    protected BitSet bitSet;
    protected int intValue;
    protected String stringValue;
    protected double doubleValue;
    protected long longValue;
    protected short shortValue;

    public PortableImpl() {
        bitSet = new BitSet(5);
        this.intValue = 0;
        this.stringValue = null;
        this.doubleValue = 0.0;
        this.longValue = 0L;
        this.shortValue = (short)0;
    }

    public static PortableImpl generate() {
        return generate(random.nextInt(0x0000001F));
    }

    public static PortableImpl generate(int flags) {
        PortableImpl result = new PortableImpl();
        if( 0 < (flags & 0x00000001) ) result.setIntValue(random.nextInt());
        if( 0 < (flags & 0x00000002) ) result.setStringValue(String.valueOf(random.nextLong()));
        if( 0 < (flags & 0x00000004) ) result.setDoubleValue(random.nextDouble());
        if( 0 < (flags & 0x00000008) ) result.setLongValue(random.nextLong());
        if( 0 < (flags & 0x00000010) ) result.setShortValue((short)random.nextInt(Short.MAX_VALUE));
        return result;
    }

    public PortableImpl setIntValue(int intValue) {
        bitSet.set(0);
        this.intValue = intValue;
        return this;
    }

    public PortableImpl setStringValue(String stringValue) {
        bitSet.set(1);
        this.stringValue = stringValue;
        return this;
    }

    public PortableImpl setDoubleValue(double doubleValue) {
        bitSet.set(2);
        this.doubleValue = doubleValue;
        return this;
    }

    public PortableImpl setLongValue(long longValue) {
        bitSet.set(3);
        this.longValue = longValue;
        return this;
    }

    public PortableImpl setShortValue(short shortValue) {
        bitSet.set(4);
        this.shortValue = shortValue;
        return this;
    }

    public int getIntValue() {
        return intValue;
    }

    public String getStringValue() {
        return stringValue;
    }

    public double getDoubleValue() {
        return doubleValue;
    }

    public long getLongValue() {
        return longValue;
    }

    public short getShortValue() {
        return shortValue;
    }

    public int getFactoryId() {
        return PortableFactoryImpl.FACTORY_ID;
    }

    public int getClassId() {
        return PortableFactoryImpl.PORTABLE_CLASS_ID;
    }

    public void writePortable(PortableWriter writer) throws IOException {
        writer.writeByteArray(BITSET, bitSet.toByteArray());
        if( bitSet.get(0) )
            writer.writeInt(INT_VALUE, intValue);
        if( bitSet.get(1) )
            writer.writeUTF(STRING_VALUE, stringValue);
        if( bitSet.get(2) )
            writer.writeDouble(DOUBLE_VALUE, doubleValue);
        if( bitSet.get(3) )
            writer.writeLong(LONG_VALUE, longValue);
        if( bitSet.get(4) )
            writer.writeShort(SHORT_VALUE, shortValue);
    }

    public void readPortable(PortableReader reader) throws IOException {
        bitSet = BitSet.valueOf(reader.readByteArray(BITSET));
        if( bitSet.get(0) )
            intValue = reader.readInt(INT_VALUE);
        if( bitSet.get(1) )
            stringValue = reader.readUTF(STRING_VALUE);
        if( bitSet.get(2) )
            doubleValue = reader.readDouble(DOUBLE_VALUE);
        if( bitSet.get(3) )
            longValue = reader.readLong(LONG_VALUE);
        if( bitSet.get(4) )
            shortValue = reader.readShort(SHORT_VALUE);
    }

    @Override
    public boolean equals(final Object object) {
        if (object == null) return false;
        if (getClass() != object.getClass()) return false;
        final PortableImpl other = (PortableImpl) object;
        return Objects.equals(intValue, other.intValue) &&
                Objects.equals(stringValue, other.stringValue) &&
                Objects.equals(doubleValue, other.doubleValue) &&
                Objects.equals(longValue, other.longValue) &&
                Objects.equals(shortValue, other.shortValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(intValue, stringValue, doubleValue, longValue, shortValue);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(PortableImpl.class)
                .add(BITSET, bitSet)
                .add(INT_VALUE, intValue)
                .add(STRING_VALUE, stringValue)
                .add(DOUBLE_VALUE, doubleValue)
                .add(LONG_VALUE, longValue)
                .add(SHORT_VALUE, shortValue)
                .toString();
    }

}
