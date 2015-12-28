/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package flex.messaging.log;

import flex.messaging.util.PrettyPrinter;
import flex.messaging.util.StringUtils;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * The <code>Logger</code> class is used to log out information. It provides named
 * methods to log information out at the desired level. Each <code>Logger</code>
 * will log information out for a log category that is settable.
 *
 *
 */
public class Logger
{
    /**
     * The category this logger send messages for.
     */
    private volatile String category;

    /**
     * The list of targets that this logger will dispatch log events to.
     */
    private final ArrayList targets;

    /**
     * Constructs a <code>Logger</code> instance that will log
     * information out to the specified category.
     *
     * @param category The category to log information for.
     */
    public Logger(String category)
    {
        this.category = category;
        targets = new ArrayList();
    }

    /**
     * Returns the category this <code>Logger</code> logs information for.
     *
     * @return The category this <code>Logger</code> logs information for.
     */
    public String getCategory()
    {
        return category;
    }

    /**
     * Determines whether the <code>Logger</code> has at least one target.
     * 
     * @return True if the <code>Logger</code> has one or more targets.
     */
    public boolean hasTarget()
    {
        synchronized (targets)
        {
            return !targets.isEmpty();
        }
    }
    /**
     * Adds a <code>Target</code> that will format and output log events
     * generated by this <code>Logger</code>.
     *
     * @param target The <code>Target</code> to add.
     */
    void addTarget(Target target)
    {
        synchronized (targets)
        {
            if (!targets.contains(target))
                targets.add(target);
        }
    }

    /**
     * Removes a <code>Target</code> from this <code>Logger</code>.
     *
     * @param target The <code>Target</code> to remove.
     */
    void removeTarget(Target target)
    {
        synchronized (targets)
        {
            targets.remove(target);
        }
    }

    /*
     *  DEBUG
     */
    /**
     * Logs out a debug message.
     *
     * @param message The message to log.
     */
    public void debug(String message)
    {
        log(LogEvent.DEBUG, message, null, null);
    }

    /**
     * Logs out a debug message associated with a <code>Throwable</code>.
     *
     * @param message The message to log.
     * @param t The associated <code>Throwable</code>.
     */
    public void debug(String message, Throwable t)
    {
        log(LogEvent.DEBUG, message, null, t);
    }

    /**
     * Logs out a debug message supporting positional parameter substitutions.
     *
     * @param message The message to log.
     * @param parameters Parameters to substitute into the message.
     */
    public void debug(String message, Object[] parameters)
    {
        log(LogEvent.DEBUG, message, parameters, null);
    }

    /**
     * Logs out a debug message supporting positional parameter substitutions and an
     * associated <code>Throwable</code>.
     *
     * @param message The message to log.
     * @param parameters Parameters to substitute into the message.
     * @param t The associated <code>Throwable</code>.
     */
    public void debug(String message, Object[] parameters, Throwable t)
    {
        log(LogEvent.DEBUG, message, parameters, t);
    }

    /*
     *  INFO
     */
    /**
     * Logs out an info message.
     *
     * @param message The message to log.
     */
    public void info(String message)
    {
        log(LogEvent.INFO, message, null, null);
    }

    /**
     * Logs out an info message associated with a <code>Throwable</code>.
     *
     * @param message The message to log.
     * @param t The associated <code>Throwable</code>.
     */
    public void info(String message, Throwable t)
    {
        log(LogEvent.INFO, message, null, t);
    }

    /**
     * Logs out an info message supporting positional parameter substitutions.
     *
     * @param message The message to log.
     * @param parameters Parameters to substitute into the message.
     */
    public void info(String message, Object[] parameters)
    {
        log(LogEvent.INFO, message, parameters, null);
    }

    /**
     * Logs out an info message supporting positional parameter substitutions and an
     * associated <code>Throwable</code>.
     *
     * @param message The message to log.
     * @param parameters Parameters to substitute into the message.
     * @param t The associated <code>Throwable</code>.
     */
    public void info(String message, Object[] parameters, Throwable t)
    {
        log(LogEvent.INFO, message, parameters, t);
    }

    /*
     *  WARN
     */
    /**
     * Logs out a warn message.
     *
     * @param message The message to log.
     */
    public void warn(String message)
    {
        log(LogEvent.WARN, message, null, null);
    }

    /**
     * Logs out a warn message associated with a <code>Throwable</code>.
     *
     * @param message The message to log.
     * @param t The associated <code>Throwable</code>.
     */
    public void warn(String message, Throwable t)
    {
        log(LogEvent.WARN, message, null, t);
    }

    /**
     * Logs out a warn message supporting positional parameter substitutions.
     *
     * @param message The message to log.
     * @param parameters Parameters to substitute into the message.
     */
    public void warn(String message, Object[] parameters)
    {
        log(LogEvent.WARN, message, parameters, null);
    }

    /**
     * Logs out a warn message supporting positional parameter substitutions and an
     * associated <code>Throwable</code>.
     *
     * @param message The message to log.
     * @param parameters Parameters to substitute into the message.
     * @param t The associated <code>Throwable</code>.
     */
    public void warn(String message, Object[] parameters, Throwable t)
    {
        log(LogEvent.WARN, message, parameters, t);
    }

    /*
     *  ERROR
     */
    /**
     * Logs out an error message.
     *
     * @param message The message to log.
     */
    public void error(String message)
    {
        log(LogEvent.ERROR, message, null, null);
    }

    /**
     * Logs out an error message associated with a <code>Throwable</code>.
     *
     * @param message The message to log.
     * @param t The associated <code>Throwable</code>.
     */
    public void error(String message, Throwable t)
    {
        log(LogEvent.ERROR, message, null, t);
    }

    /**
     * Logs out an error message supporting positional parameter substitutions.
     *
     * @param message The message to log.
     * @param parameters Parameters to substitute into the message.
     */
    public void error(String message, Object[] parameters)
    {
        log(LogEvent.ERROR, message, parameters, null);
    }

    /**
     * Logs out an error message supporting positional parameter substitutions and an
     * associated <code>Throwable</code>.
     *
     * @param message The message to log.
     * @param parameters Parameters to substitute into the message.
     * @param t The associated <code>Throwable</code>.
     */
    public void error(String message, Object[] parameters, Throwable t)
    {
        log(LogEvent.ERROR, message, parameters, t);
    }

    /*
    *  FATAL
    */
    /**
     * Logs out a fatal message.
     *
     * @param message The message to log.
     */
    public void fatal(String message)
    {
        log(LogEvent.FATAL, message, null, null);
    }

    /**
     * Logs out a fatal message associated with a <code>Throwable</code>.
     *
     * @param message The message to log.
     * @param t The associated <code>Throwable</code>.
     */
    public void fatal(String message, Throwable t)
    {
        log(LogEvent.FATAL, message, null, t);
    }

    /**
     * Logs out a fatal message supporting positional parameter substitutions.
     *
     * @param message The message to log.
     * @param parameters Parameters to substitute into the message.
     */
    public void fatal(String message, Object[] parameters)
    {
        log(LogEvent.FATAL, message, parameters, null);
    }

    /**
     * Logs out a fatal message supporting positional parameter substitutions and an
     * associated <code>Throwable</code>.
     *
     * @param message The message to log.
     * @param parameters Parameters to substitute into the message.
     * @param t The associated <code>Throwable</code>.
     */
    public void fatal(String message, Object[] parameters, Throwable t)
    {
        log(LogEvent.FATAL, message, parameters, t);
    }

    /**
     *
     * The methods named according to log level delegate to this method to log.
     *
     * @param level The log level.
     * @param message The message to log.
     * @param parameters Substitution parameters (may be null).
     * @param t The associated <code>Throwable</code> (may be null).
     */
    public void log(short level, String message, Object[] parameters, Throwable t)
    {
        log(level, message, parameters, t, true);
    }

    /**
     *
     * Logs a passed message if its level verifies as high enough.
     *
     * @param level The log level.
     * @param message The message to log.
     * @param parameters Substitution parameters (may be null).
     * @param t The associated <code>Throwable</code>.
     * @param verifyLevel <code>true</code> to verify the log level; otherwise log without verifying the level.
     */
    public void log(short level, String message, Object[] parameters, Throwable t, boolean verifyLevel)
    {
        if (targets.size() > 0 && (!verifyLevel || (level >= Log.getTargetLevel())))
        {
            if (parameters != null)
            {
                PrettyPrinter prettyPrinter = Log.getPrettyPrinter();

                // replace all of the parameters in the msg string
                for(int i = 0; i < parameters.length; i++)
                {
                    String replacement = parameters[i] != null ? prettyPrinter.prettify(parameters[i]) : "null";

                    //this guy runs into problems if the replacement has a \ or $ in it
                    //message = message.replaceAll("\\{" + i + "\\}", replacement);
                    message = StringUtils.substitute(message, "{" + i + "}", replacement);
                }
            }
            LogEvent event = new LogEvent(this, message, level, t);
            Target tgt;
            synchronized (targets)
            {
                for (Iterator iter = targets.iterator(); iter.hasNext();)
                {
                    tgt = (Target) iter.next();
                    if (!verifyLevel || (level >= tgt.getLevel()))
                        tgt.logEvent(event);
                }
            }
        }
    }

}