package helpers_J;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;

public class ArgumentNamesExtractor
{
    public static String[] getArgumentNames(Method method)throws Exception
    {
        Class<?> declaringClass = method.getDeclaringClass();
        ClassLoader classLoader = declaringClass.getClassLoader();
        String className = declaringClass.getName().replace(".", "/");
        InputStream classBytecode = classLoader.getResourceAsStream(className + ".class");

        // Vérification que le bytecode est trouvé
        if (classBytecode == null)
        {
            throw new IOException("Bytecode not found for class: " + declaringClass.getName());
        }

        try
        {

            ClassReader classReader = new ClassReader(classBytecode);

            final String[] argumentNames = new String[method.getParameterCount()];
            classReader.accept(new ClassVisitor(Opcodes.ASM7)
            {
                @Override
                public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
//                if (name.equals(method.getName()) && descriptor.equals(getMethodDescriptor(method)))
                    if (name.equals(method.getName()) && descriptor.equals(getMethodDescriptor(method)))
                    {
                        return new MethodVisitor(Opcodes.ASM7)
                        {
                            @Override
                            public void visitLocalVariable(String name, String descriptor, String signature, org.objectweb.asm.Label start, org.objectweb.asm.Label end, int index)
                            {
//                                System.out.println(method.getName() + "-----" +name);
                                if (!name.equals("this") && !name.equals("i"))
                                {
                                    argumentNames[index - 1] = name;
                                }
                            }
                        };
                    }
                    return super.visitMethod(access, name, descriptor, signature, exceptions);
                }
            }, 0);

            return argumentNames;
        }
        catch (Exception ex)
        {
            throw new Exception(ex.getMessage());
        }
    }

    private static String getMethodDescriptor(Method method)
    {
        StringBuilder descriptor = new StringBuilder();
        descriptor.append("(");
        for (Class<?> parameterType : method.getParameterTypes())
        {
            descriptor.append(getTypeDescriptor(parameterType));
        }
        descriptor.append(")");
        descriptor.append(getTypeDescriptor(method.getReturnType()));
        return descriptor.toString();
    }

    private static String getTypeDescriptor(Class<?> type)
    {
        if (type.isPrimitive())
        {
            if (type == void.class)
            {
                return "V";
            }
            else if (type == boolean.class)
            {
                return "Z";
            } else if (type == byte.class)
            {
                return "B";
            }
            else if (type == char.class)
            {
                return "C";
            }
            else if (type == short.class)
            {
                return "S";
            }
            else if (type == int.class)
            {
                return "I";
            }
            else if (type == long.class)
            {
                return "J";
            }
            else if (type == float.class)
            {
                return "F";
            }
            else if (type == double.class)
            {
                return "D";
            }
        }
        return "L" + type.getName().replace(".", "/") + ";";
    }
}