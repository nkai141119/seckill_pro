package com.kai.miaosha.util;
import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;
/**
 * The annotated element must not be {@code null}.
 * Accepts any type.
 *
 * @author Emmanuel Bernard
 */
/**
 * 1、ElementType.CONSTRUCTOR:用于描述构造器
 * 2、ElementType.FIELD:成员变量、对象、属性（包括enum实例）
 * 3、ElementType.LOCAL_VARIABLE:用于描述局部变量
 * 4、ElementType.METHOD:用于描述方法
 * 5、ElementType.PACKAGE:用于描述包
 * 6、ElementType.PARAMETER:用于描述参数
 * 7、ElementType.TYPE:用于描述类、接口(包括注解类型) 或enum声明
 *
 * @Retention 注解:他的作用是为了说明这个注解的生命周期，在注解中有三个生命周期
 * 1、RetentionPolicy.RUNTIME : 始终不会丢弃，运行期也保留该注解，因此可以使用反射机制读取该注解的信息。我们自定义的注解通常使用这种方式。
 * 2、RetentionPolicy.CLASS : 在类加载的时候丢弃。在字节码文件的处理中有用。注解默认使用这种方式
 * 3、RetentionPolicy.SOURCE : 在编译阶段丢弃。这些注解在编译结束之后就不再有任何意义，所以它们不会写入字节码。
 * @Override, @SuppressWarnings都属于这类注解。
 *
 * @Inherited 元注解是一个标记注解，@Inherited阐述了某个被标注的类型是被继承的。
 * 如果一个使用了@Inherited修饰的annotation类型被用于一个class，则这个annotation将被用于该class的子类。
 *
 * @Documented–一个简单的Annotations标记注解，表示是否将注解信息添加在java文档中。
 *
 * 使用@Constraint 进行修饰指定校验策略.
 * */
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
//Target表示修饰的目标，都是Java自带枚举类ElementType的元素
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = { IsMobileValidator.class })//继承校验器
public @interface IsMobile {
	boolean required() default true;
	String message() default "手机号码格式有误!";
	Class<?>[] groups() default { };
	Class<? extends Payload>[] payload() default { };
}
