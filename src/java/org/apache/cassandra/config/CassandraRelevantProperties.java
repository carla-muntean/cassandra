/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.cassandra.config;

import java.util.concurrent.TimeUnit;

import org.apache.cassandra.exceptions.ConfigurationException;

/** A class that extracts system properties for the cassandra node it runs within. */
public enum CassandraRelevantProperties
{
    //base JVM properties
    JAVA_HOME("java.home"),
    CASSANDRA_PID_FILE ("cassandra-pidfile"),

    /**
     * Indicates the temporary directory used by the Java Virtual Machine (JVM)
     * to create and store temporary files.
     */
    JAVA_IO_TMPDIR ("java.io.tmpdir"),

    /**
     * Path from which to load native libraries.
     * Default is absolute path to lib directory.
     */
    JAVA_LIBRARY_PATH ("java.library.path"),

    JAVA_SECURITY_EGD ("java.security.egd"),

    /** Java Runtime Environment version */
    JAVA_VERSION ("java.version"),

    /** Java Virtual Machine implementation name */
    JAVA_VM_NAME ("java.vm.name"),

    /** Line separator ("\n" on UNIX). */
    LINE_SEPARATOR ("line.separator"),

    /** Java class path. */
    JAVA_CLASS_PATH ("java.class.path"),

    /** Operating system architecture. */
    OS_ARCH ("os.arch"),

    /** Operating system name. */
    OS_NAME ("os.name"),

    /** User's home directory. */
    USER_HOME ("user.home"),

    /** Platform word size sun.arch.data.model. Examples: "32", "64", "unknown"*/
    SUN_ARCH_DATA_MODEL ("sun.arch.data.model"),

    //JMX properties
    /**
     * The value of this property represents the host name string
     * that should be associated with remote stubs for locally created remote objects,
     * in order to allow clients to invoke methods on the remote object.
     */
    JAVA_RMI_SERVER_HOSTNAME ("java.rmi.server.hostname"),

    /**
     * If this value is true, object identifiers for remote objects exported by this VM will be generated by using
     * a cryptographically secure random number generator. The default value is false.
     */
    JAVA_RMI_SERVER_RANDOM_ID ("java.rmi.server.randomIDs"),

    /**
     * This property indicates whether password authentication for remote monitoring is
     * enabled. By default it is disabled - com.sun.management.jmxremote.authenticate
     */
    COM_SUN_MANAGEMENT_JMXREMOTE_AUTHENTICATE ("com.sun.management.jmxremote.authenticate"),

    /**
     * The port number to which the RMI connector will be bound - com.sun.management.jmxremote.rmi.port.
     * An Integer object that represents the value of the second argument is returned
     * if there is no port specified, if the port does not have the correct numeric format,
     * or if the specified name is empty or null.
     */
    COM_SUN_MANAGEMENT_JMXREMOTE_RMI_PORT ("com.sun.management.jmxremote.rmi.port", "0"),

    /** Cassandra jmx remote port */
    CASSANDRA_JMX_REMOTE_PORT("cassandra.jmx.remote.port"),

    /** Cassandra jmx local port */
    CASSANDRA_JMX_LOCAL_PORT("cassandra.jmx.local.port"),

    /** This property  indicates whether SSL is enabled for monitoring remotely. Default is set to false. */
    COM_SUN_MANAGEMENT_JMXREMOTE_SSL ("com.sun.management.jmxremote.ssl"),

    /**
     * This property indicates whether SSL client authentication is enabled - com.sun.management.jmxremote.ssl.need.client.auth.
     * Default is set to false.
     */
    COM_SUN_MANAGEMENT_JMXREMOTE_SSL_NEED_CLIENT_AUTH ("com.sun.management.jmxremote.ssl.need.client.auth"),

    /**
     * This property indicates the location for the access file. If com.sun.management.jmxremote.authenticate is false,
     * then this property and the password and access files, are ignored. Otherwise, the access file must exist and
     * be in the valid format. If the access file is empty or nonexistent, then no access is allowed.
     */
    COM_SUN_MANAGEMENT_JMXREMOTE_ACCESS_FILE ("com.sun.management.jmxremote.access.file"),

    /** This property indicates the path to the password file - com.sun.management.jmxremote.password.file */
    COM_SUN_MANAGEMENT_JMXREMOTE_PASSWORD_FILE ("com.sun.management.jmxremote.password.file"),

    /** Port number to enable JMX RMI connections - com.sun.management.jmxremote.port */
    COM_SUN_MANAGEMENT_JMXREMOTE_PORT ("com.sun.management.jmxremote.port"),

    /**
     * A comma-delimited list of SSL/TLS protocol versions to enable.
     * Used in conjunction with com.sun.management.jmxremote.ssl - com.sun.management.jmxremote.ssl.enabled.protocols
     */
    COM_SUN_MANAGEMENT_JMXREMOTE_SSL_ENABLED_PROTOCOLS ("com.sun.management.jmxremote.ssl.enabled.protocols"),

    /**
     * A comma-delimited list of SSL/TLS cipher suites to enable.
     * Used in conjunction with com.sun.management.jmxremote.ssl - com.sun.management.jmxremote.ssl.enabled.cipher.suites
     */
    COM_SUN_MANAGEMENT_JMXREMOTE_SSL_ENABLED_CIPHER_SUITES ("com.sun.management.jmxremote.ssl.enabled.cipher.suites"),

    /** mx4jaddress */
    MX4JADDRESS ("mx4jaddress"),

    /** mx4jport */
    MX4JPORT ("mx4jport"),

    RING_DELAY("cassandra.ring_delay_ms", "30000"),

    MIGRATION_DELAY("cassandra.migration_delay_ms", "60000"),

    /**
     * When bootstraping we wait for all schema versions found in gossip to be seen, and if not seen in time we fail
     * the bootstrap; this property will avoid failing and allow bootstrap to continue if set to true.
     */
    BOOTSTRAP_SKIP_SCHEMA_CHECK("cassandra.skip_schema_check"),

    /**
     * When bootstraping how long to wait for schema versions to be seen.
     */
    BOOTSTRAP_SCHEMA_DELAY_MS("cassandra.schema_delay_ms"),

    /**
     * When draining, how long to wait for mutating executors to shutdown.
     */
    DRAIN_EXECUTOR_TIMEOUT_MS("cassandra.drain_executor_timeout_ms", String.valueOf(TimeUnit.MINUTES.toMillis(5))),

    /**
     * Gossip quarantine delay is used while evaluating membership changes and should only be changed with extreme care.
     */
    GOSSIPER_QUARANTINE_DELAY("cassandra.gossip_quarantine_delay_ms"),

    IGNORED_SCHEMA_CHECK_VERSIONS("cassandra.skip_schema_check_for_versions"),

    IGNORED_SCHEMA_CHECK_ENDPOINTS("cassandra.skip_schema_check_for_endpoints"),

    /**
     * Factory to create instances used during log transaction processing
     */
    LOG_TRANSACTIONS_FACTORY("cassandra.log_transactions_factory"),

    /**
     * When doing a host replacement its possible that the gossip state is "empty" meaning that the endpoint is known
     * but the current state isn't known.  If the host replacement is needed to repair this state, this property must
     * be true.
     */
    REPLACEMENT_ALLOW_EMPTY("cassandra.allow_empty_replace_address", "true"),

    /**
     * Whether {@link org.apache.cassandra.db.ConsistencyLevel#NODE_LOCAL} should be allowed.
     */
    ENABLE_NODELOCAL_QUERIES("cassandra.enable_nodelocal_queries", "false"),

    CONSISTENT_DIRECTORY_LISTINGS("cassandra.consistent_directory_listings", "false"),

    /**
     * To provide custom implementation to prioritize compaction tasks in UCS
     */
    UCS_COMPACTION_AGGREGATE_PRIORITIZER("unified_compaction.custom_compaction_aggregate_prioritizer"),

    /**
     * The handler of the storage of sstables, and possibly other files such as txn logs.
     */
    REMOTE_STORAGE_HANDLER("cassandra.remote_storage_handler"),

    /**
     * To provide a provider to a different implementation of the truncate statement.
     */
    TRUNCATE_STATEMENT_PROVIDER("cassandra.truncate_statement_provider"),

    /**
     * custom native library for os access
     */
    CUSTOM_NATIVE_LIBRARY("cassandra.custom_native_library"),

    /**
     * Repair progress reporter, default using system distributed keyspace
     */
    REPAIR_PROGRESS_REPORTER("cassandra.repair_progress_reporter_class"),

    /**
     * Listen to repair parent session lifecycle
     */
    REPAIR_PARENT_SESSION_LISTENER("cassandra.custom_parent_repair_session_listener_class"),

    //cassandra properties (without the "cassandra." prefix)

    /**
     * The cassandra-foreground option will tell CassandraDaemon whether
     * to close stdout/stderr, but it's up to us not to background.
     * yes/null
     */
    CASSANDRA_FOREGROUND ("cassandra-foreground"),

    DEFAULT_PROVIDE_OVERLAPPING_TOMBSTONES ("default.provide.overlapping.tombstones"),
    ORG_APACHE_CASSANDRA_DISABLE_MBEAN_REGISTRATION ("org.apache.cassandra.disable_mbean_registration"),
    //only for testing
    ORG_APACHE_CASSANDRA_CONF_CASSANDRA_RELEVANT_PROPERTIES_TEST("org.apache.cassandra.conf.CassandraRelevantPropertiesTest"),
    ORG_APACHE_CASSANDRA_DB_VIRTUAL_SYSTEM_PROPERTIES_TABLE_TEST("org.apache.cassandra.db.virtual.SystemPropertiesTableTest"),

    /** This property indicates whether disable_mbean_registration is true */
    IS_DISABLED_MBEAN_REGISTRATION("org.apache.cassandra.disable_mbean_registration"),

    /** what class to use for mbean registeration */
    MBEAN_REGISTRATION_CLASS("org.apache.cassandra.mbean_registration_class"),

    /** Which class to use for token metadata provider */
    CUSTOM_TMD_PROVIDER_PROPERTY("cassandra.custom_token_metadata_provider_class"),

    /** Which class to use for failure detection */
    CUSTOM_FAILURE_DETECTOR_PROPERTY("cassandra.custom_failure_detector_class"),

    /** Set this property to true in order to switch to micrometer metrics */
    USE_MICROMETER("cassandra.use_micrometer_metrics", "false"),

    /** Which class to use for coordinator client request metrics */
    CUSTOM_CLIENT_REQUEST_METRICS_PROVIDER_PROPERTY("cassandra.custom_client_request_metrics_provider_class"),

    /**
     * Which class to use for creating guardrails
     */
    CUSTOM_GUARDRAILS_FACTORY_PROPERTY("cassandra.custom_guardrails_factory_class"),

    /**
     * Used to support directory creation for different file system and remote/local conversion
     */
    CUSTOM_STORAGE_PROVIDER("cassandra.custom_storage_provider"),

    /** Watcher used when opening sstables to discover extra components, eg. archive component */
    CUSTOM_SSTABLE_WATCHER("cassandra.custom_sstable_watcher"),

    /**
     * Whether to disable auto-compaction
     */
    DISABLED_AUTO_COMPACTION_PROPERTY("cassandra.disabled_auto_compaction"),

    /** Which class to use for dynamic snitch severity values */
    DYNAMIC_SNITCH_SEVERITY_PROVIDER("cassandra.dynamic_snitch_severity_provider"),

    NEVER_PURGE_TOMBSTONES_PROPERTY("cassandra.never_purge_tombstones"),

    USE_NIX_RECURSIVE_DELETE("cassandra.use_nix_recursive_delete"),

    SYSTEM_DISTRIBUTED_NTS_RF_OVERRIDE_PROPERTY("cassandra.system_distributed_replication_per_dc"),
    SYSTEM_DISTRIBUTED_NTS_DC_OVERRIDE_PROPERTY("cassandra.system_distributed_replication_dc_names"),

    ;



    CassandraRelevantProperties(String key, String defaultVal)
    {
        this.key = key;
        this.defaultVal = defaultVal;
    }

    CassandraRelevantProperties(String key)
    {
        this.key = key;
        this.defaultVal = null;
    }

    private final String key;
    private final String defaultVal;

    public String getKey()
    {
        return key;
    }

    /**
     * Gets the value of the indicated system property.
     * @return system property value if it exists, defaultValue otherwise.
     */
    public String getString()
    {
        String value = System.getProperty(key);

        return value == null ? defaultVal : STRING_CONVERTER.convert(value);
    }

    /**
     * Gets the value of a system property as a String.
     * @return system property String value if it exists, overrideDefaultValue otherwise.
     */
    public String getString(String overrideDefaultValue)
    {
        String value = System.getProperty(key);
        if (value == null)
            return overrideDefaultValue;

        return STRING_CONVERTER.convert(value);
    }

    /**
     * Sets the value into system properties.
     * @param value to set
     */
    public void setString(String value)
    {
        System.setProperty(key, value);
    }

    /**
     * Gets the value of a system property as a boolean.
     * @return system property boolean value if it exists, false otherwise().
     */
    public boolean getBoolean()
    {
        String value = System.getProperty(key);

        return BOOLEAN_CONVERTER.convert(value == null ? defaultVal : value);
    }

    /**
     * Gets the value of a system property as a boolean.
     * @return system property boolean value if it exists, overrideDefaultValue otherwise.
     */
    public boolean getBoolean(boolean overrideDefaultValue)
    {
        String value = System.getProperty(key);
        if (value == null)
            return overrideDefaultValue;

        return BOOLEAN_CONVERTER.convert(value);
    }

    /**
     * Sets the value into system properties.
     * @param value to set
     */
    public void setBoolean(boolean value)
    {
        System.setProperty(key, Boolean.toString(value));
    }

    /**
     * Gets the value of a system property as a int.
     * @return system property int value if it exists, defaultValue otherwise.
     */
    public int getInt()
    {
        String value = System.getProperty(key);

        return INTEGER_CONVERTER.convert(value == null ? defaultVal : value);
    }

    /**
     * Gets the value of a system property as a int.
     * @return system property int value if it exists, overrideDefaultValue otherwise.
     */
    public int getInt(int overrideDefaultValue)
    {
        String value = System.getProperty(key);
        if (value == null)
            return overrideDefaultValue;

        return INTEGER_CONVERTER.convert(value);
    }

    /**
     * Sets the value into system properties.
     * @param value to set
     */
    public void setInt(int value)
    {
        System.setProperty(key, Integer.toString(value));
    }

    /**
     * Gets the value of a system property as a long.
     * @return system property long value if it exists, defaultValue otherwise.
     */
    public long getLong()
    {
        String value = System.getProperty(key);

        return LONG_CONVERTER.convert(value == null ? defaultVal : value);
    }

    /**
     * Gets the value of a system property as a long.
     * @return system property long value if it exists, overrideDefaultValue otherwise.
     */
    public long getLong(int overrideDefaultValue)
    {
        String value = System.getProperty(key);
        if (value == null)
            return overrideDefaultValue;

        return LONG_CONVERTER.convert(value);
    }

    /**
     * Sets the value into system properties.
     * @param value to set
     */
    public void setLong(long value)
    {
        System.setProperty(key, Long.toString(value));
    }

    private interface PropertyConverter<T>
    {
        T convert(String value);
    }

    private static final PropertyConverter<String> STRING_CONVERTER = value -> value;

    private static final PropertyConverter<Boolean> BOOLEAN_CONVERTER = Boolean::parseBoolean;

    private static final PropertyConverter<Integer> INTEGER_CONVERTER = value ->
    {
        try
        {
            return Integer.decode(value);
        }
        catch (NumberFormatException e)
        {
            throw new ConfigurationException(String.format("Invalid value for system property: " +
                                                           "expected integer value but got '%s'", value));
        }
    };

    private static final PropertyConverter<Long> LONG_CONVERTER = value ->
    {
        try
        {
            return Long.decode(value);
        }
        catch (NumberFormatException e)
        {
            throw new ConfigurationException(String.format("Invalid value for system property: " +
                                                           "expected integer value but got '%s'", value));
        }
    };

    private static final PropertyConverter<Double> DOUBLE_CONVERTER = value ->
    {
        try
        {
            return Double.parseDouble(value);
        }
        catch (NumberFormatException e)
        {
            throw new ConfigurationException(String.format("Invalid value for system property: " +
                                                           "expected double value but got '%s'", value));
        }
    };

    /**
     * @return whether a system property is present or not.
     */
    public boolean isPresent()
    {
        return System.getProperties().containsKey(key);
    }
}

