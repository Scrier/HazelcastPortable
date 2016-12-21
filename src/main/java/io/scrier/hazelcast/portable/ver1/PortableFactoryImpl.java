package io.scrier.hazelcast.portable.ver1;

import com.hazelcast.config.Config;
import com.hazelcast.nio.serialization.ClassDefinition;
import com.hazelcast.nio.serialization.ClassDefinitionBuilder;
import com.hazelcast.nio.serialization.Portable;
import com.hazelcast.nio.serialization.PortableFactory;

/**
 * TBD
 */
public class PortableFactoryImpl implements PortableFactory {

    public static final int FACTORY_ID = 1;

    public static final int PORTABLE_CLASS_ID = 1;

    public static Config populate(Config config) {
        config.getSerializationConfig().addPortableFactory(FACTORY_ID, new PortableFactoryImpl())
                .setPortableVersion(1)
                .addClassDefinition(createPortableDefinition());
        return config;
    }

    public Portable create(int classId) {
        if( PORTABLE_CLASS_ID == classId) {
            return new PortableImpl();
        } else {
            return null;
        }
    }

    public static ClassDefinition createPortableDefinition() {
        return new ClassDefinitionBuilder(FACTORY_ID, PORTABLE_CLASS_ID, 1)
                .addByteArrayField(PortableImpl.BITSET)
                .addIntField(PortableImpl.INT_VALUE)
                .addUTFField(PortableImpl.STRING_VALUE)
                .addLongField(PortableImpl.LONG_VALUE)
                .addDoubleField(PortableImpl.DOUBLE_VALUE)
                .build();
    }

}
