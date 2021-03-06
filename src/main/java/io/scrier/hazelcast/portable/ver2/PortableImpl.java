package io.scrier.hazelcast.portable.ver2;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.hazelcast.nio.serialization.Portable;
import com.hazelcast.nio.serialization.PortableReader;
import com.hazelcast.nio.serialization.PortableWriter;
import io.scrier.hazelcast.portable.BitSetHelper;

import java.io.IOException;
import java.util.BitSet;
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
        StringBuilder builder = new StringBuilder("Generating: ");
        if( 0 < (flags & 0x00000001) ) {
            result.setIntValue(random.nextInt());
            builder.append(INT_VALUE + ":");
        }
        if( 0 < (flags & 0x00000002) ) {
            result.setStringValue(String.valueOf(random.nextLong()));
            builder.append(STRING_VALUE + ":");
        }
        if( 0 < (flags & 0x00000004) ) {
            result.setDoubleValue(random.nextDouble());
            builder.append(DOUBLE_VALUE + ":");
        }
        if( 0 < (flags & 0x00000008) ) {
            result.setLongValue(random.nextLong());
            builder.append(LONG_VALUE + ":");
        }
        if( 0 < (flags & 0x00000010) ) {
            result.setShortValue((short)random.nextInt(Short.MAX_VALUE));
            builder.append(SHORT_VALUE + ":");
        }
        System.out.println(builder.toString());
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
        if( bitSet.get(0) ) {
            writer.writeInt(INT_VALUE, intValue);
            System.out.println("Writing: " + INT_VALUE);
        }
        if( bitSet.get(1) ) {
            writer.writeUTF(STRING_VALUE, stringValue);
            System.out.println("Writing: " + STRING_VALUE);
        }
        if( bitSet.get(2) ) {
            writer.writeDouble(DOUBLE_VALUE, doubleValue);
            System.out.println("Writing: " + DOUBLE_VALUE);
        }
        if( bitSet.get(3) ) {
            writer.writeLong(LONG_VALUE, longValue);
            System.out.println("Writing: " + LONG_VALUE);
        }
        if( bitSet.get(4) ) {
            writer.writeShort(SHORT_VALUE, shortValue);
            System.out.println("Writing: " + SHORT_VALUE);
        }
    }

    public void readPortable(PortableReader reader) throws IOException {
        bitSet = BitSet.valueOf(reader.readByteArray(BITSET));
        System.out.println(bitSet);
        if( bitSet.get(0) && reader.hasField(INT_VALUE) ) {
            intValue = reader.readInt(INT_VALUE);
            System.out.println(intValue);
        }
        if( bitSet.get(1) && reader.hasField(STRING_VALUE) ) {
            stringValue = reader.readUTF(STRING_VALUE);
            System.out.println(stringValue);
        }
        if( bitSet.get(2) && reader.hasField(DOUBLE_VALUE) ) {
            doubleValue = reader.readDouble(DOUBLE_VALUE);
            System.out.println(doubleValue);
        }
        if( bitSet.get(3) && reader.hasField(LONG_VALUE) ) {
            longValue = reader.readLong(LONG_VALUE);
            System.out.println(longValue);
        }
        if( bitSet.get(4) && reader.hasField(SHORT_VALUE) ) {
            shortValue = reader.readShort(SHORT_VALUE);
            System.out.println(shortValue);
        }
    }

    @Override
    public boolean equals(final Object object) {
        if (object == null) return false;
        if (getClass() != object.getClass()) return false;
        final PortableImpl other = (PortableImpl) object;
        return Objects.equal(intValue, other.intValue) &&
                Objects.equal(stringValue, other.stringValue) &&
                Objects.equal(doubleValue, other.doubleValue) &&
                Objects.equal(longValue, other.longValue) &&
                Objects.equal(shortValue, other.shortValue);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(intValue, stringValue, doubleValue, longValue, shortValue);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(PortableImpl.class)
                .add(BITSET, bitSet)
                .add(BITSET + "AsBytes", bitSet.toByteArray())
                .add(INT_VALUE, intValue)
                .add(STRING_VALUE, stringValue)
                .add(DOUBLE_VALUE, doubleValue)
                .add(LONG_VALUE, longValue)
                .add(SHORT_VALUE, shortValue)
                .toString();
    }

}
