# Patrones Arquitecturales

## 1. Desplegar un sitio estático usando S3
Nos dirigimos a la consola de AWS como se ilustra en la siguinete imagen:
![Cosola de administración](/images/1.png)
En la consola buscamos el servicio de S3, entramos a S3 y no aparecera la siguente pantalla.
![Cosola de administración](/images/2.png)
Damos click en el boton azul de create bucket que nos aparece en la pantalla, una vez le demos al boton nos aparecera una ventana emergente como la siguiente.
<br/>
En esta ventana escojemos el nombre que tendra el bucket y su regino, para este caso el nombre del bucket es "patronesarquitecturales" y su region US East(N. Virginia)
<br/>
**Nota:** el nombre del bucket tiene que ser unico con todos los buckets que existan en AWS.
Una vez llenado estos datos le damos en el boton de next.

![Cosola de administración](/images/3.png)
Esta ventana es para realizar la configuracion del bucket, le damos en el boton de Next.
![Cosola de administración](/images/4.png)
En este apartado podemos configurar el accesos externo hacia el bucket, por el momento no tocamos nada y le damos al boton Next.
![Cosola de administración](/images/5.png)
En este apartado revisaremos que toda las opciones esten como las modificamos, una vez confirmemos que todo este bien le damos en el boton "Create bucket".
![Cosola de administración](/images/6.png)
Una vez le dimos en **"Create bucket"**, cerramos la ventana y tendremos nuestro bucket creado como se muestra en la siguiente imagen.
![Cosola de administración](/images/7.png)
Entramos al bucket y aparecela la siguiente ventana.
![Cosola de administración](/images/8.png)
Dentro del bucket le damos el boton de Upload para montar nuestro sitio estatico con todo sus recursos.
![Cosola de administración](/images/9.png)
Arrastramos todo los archivos del sitio, en este caso el index.html y la carpeta de las imagenes donde se encuentran todas las imagenes de este sitio.
<br/>
Le damos en el boton Upload para cargar los archivos.

![Cosola de administración](/images/10.png)
Cuando termine de montar todos los archivos al bucket, veremos la siguente ventana.
![Cosola de administración](/images/17.png)
Nos vamos a dirigir a la pestaña que dice "Properties" para realizar la configuracion del hosting.
![Cosola de administración](/images/16.png)
Entre las opciones que nos da esta pestaña, vamos a usar el que dice "Static website hosting" como se muestra en la siguiente imagen.
<br/>
Cuando le demos click se despliegara un menu como la imagen de la derecha.
<br/>
En este formulario escojemos la opcion "Use this bucket to host a website" y en el campo de Index document pondremos el nombre de nuestro archivo html, para nuestro caso es index.html

![Cosola de administración](/images/12.png)

![Cosola de administración](/images/11.png)
Ahora nos dirigimos a la pestaña de Permissions, donde encontraremos 4 sub-ventanas, en la primera encontramos la siguiente ventana
<br/>
En esta pestraña de "Block Public Access" la damos al boton de editar y desmarcamos todas las opciones, guardamos y nos tiene que quedar de la suiguiente forma.

![Cosola de administración](/images/18.png)
Luego nos vamos a la sub-pestaña de "Bucket Policy"
<br/>
Tendremos que generar una politica de lectura para que nos permita entrar al sitio web.

![Cosola de administración](/images/19.png)
**Nota:** estas son ayudas para generar las politicas necesarias.
1. [Generador de politicas de AWS](https://awspolicygen.s3.amazonaws.com/policygen.html)
2. [Documentacion sobre las politicas](https://docs.aws.amazon.com/AmazonS3/latest/dev/example-bucket-policies.html)

Cuando le damos en el enlace de "Generador de politicas de AWS" que se encuentra en la parte de arriba nos sale la siguinete ventana.
<br/>
En esta ventana selecionamos S3 Bucket Policy como tipo de politica.
<br/>
En el apartado de primcipal ponemos **"*"** para que permita todos los archivos.
<br/>
En el apartado Actions buscamos el que dice **"GetObject"**.
<br/>
Y en el apartado Amazon Resource Name (ARN) ponemos el Arn que aparece en la consola de AWS, justo al lado **"Bucket policy editor"**.
<br/>
En este caso es **"arn:aws:s3:::patronesarquitecturales"** y le agregamos al final **"/*"**.
<br/>
Le damos al boton de "Add Statement".

![Cosola de administración](/images/15.png)
se generara una politica en la parte de abajo, al final de la pagina hay un boton que dice "Generate Policy"
<br/>
Cuando le damos nos sale la siguiente ventana.

![Cosola de administración](/images/20.png)
```
{
	"Id": "Policy1569790985535",
	"Version": "2012-10-17",
	"Statement": [
		{
		"Sid": "Stmt1569790984088",
		"Action": [
			"s3:GetObject"
		],
		"Effect": "Allow",
		"Resource": "arn:aws:s3:::patronesarquitecturales/*",
		"Principal": "*"
		}
	]
}
```
![Cosola de administración](/images/14.png)
Para terminar nos vamos a la pestaña de Cors configuracion
<br/>
Copiamos el siguiente codigo y lo pegamos en la recuadro de texto.
```
<?xml version="1.0" encoding="UTF-8"?>
<CORSConfiguration xmlns="http://s3.amazonaws.com/doc/2006-03-01/">
	<CORSRule>
		<AllowedOrigin>*</AllowedOrigin>
		<AllowedMethod>GET</AllowedMethod>
		<MaxAgeSeconds>3000</MaxAgeSeconds>
		<AllowedHeader>*</AllowedHeader>
	</CORSRule>
</CORSConfiguration>
```
Nos tiene que quedar de la siguente forma
<br/>
Una vez tengamos eso realizado guardamos

![Cosola de administración](/images/13.png)
**Nota:** para mayor informacion de la configuracion de cors entra al enlace que esta aqui abajo.
 - [Documentacion sobre los Cors](https://docs.aws.amazon.com/AmazonS3/latest/dev/cors.html)

<br/>
Hemos terminado la configuracion del bucket para probar nuestro sitio web escribimos la siguinete ruta.

```
https://<Nombre del bucket>.s3.amazonaws.com/<index>.html
```

Para nuestro caso la direccion ULR es la siguiente:
[https://patronesarquitecturales.s3.amazonaws.com/index.html](https://patronesarquitecturales.s3.amazonaws.com/index.html)


## 2. Desplegar un formulario dinámico usando EC2

1. Acceda a la consola de administración de AWS

Buscamos el servicio de EC2.
![Cosola de administración](/images/1.png)

2. Cree una maquina virtual linux siguiendo los pasos en:

Una vez adentro, le damos en el boton de Launch Instance.
![Cosola de administración](/images/21.png)
Seleccionamos la segunda opcion. (Amazon Linux AMI 2018.03.0 (HVM), SSD Volume Type)
![Cosola de EC2](/images/22.png)
Le damos en el boton de Review and Launch.
![Seleccion de Maquina](/images/23.png)
Le damos en el boton launch.
![Seleccion de tipo de instancia](/images/24.png)
Se nos abrira la siguente ventana, en esta ventana escojemos en el primer campo la opcion **"Create a new key pair"**.
En la segundo campo ponemos el nombre de como queremos que se llame la llave.
Le damos en el boton de **"Download Key Pair"**.
![Resumen del recurso a crear](/images/25.png)
Nos aparecera la siguiente ventana, mostrando que se a creado exitosamente la maquina virtual.
Bajamos hasta el final de la pagina y presionamos el boton
![Creacion de credenciales ssh](/images/26.png)
Nos redirigira a la siguinete pagina donde veremos las instancias creadas. En este caso solo tendremos la que creamos.
![Cosola de administración instancias EC2](/images/27.png)

Para mas referencias visite el siguiente enlace: 
- [Aws getting started tutorial](https://aws.amazon.com/es/getting-started/tutorials/launch-a-virtual-machine/)

3. Nos conectamos a la maquina con el siguiente commando:

Nos dirigimos a la parte superior al boton que dice Connect, esto nos desplegara la siguiente ventana:

![Cliente ssh](/images/28.png)

Este es el comando que tenemos que ejecutar en la terminal:
```
ssh -i "Credentials.pem" ec2-user@ec2-18-209-23-92.compute-1.amazonaws.com
```
![Ejecucion el comando](/images/29.png)
**Nota:** Salimos del ssh usando "exit".

4. Usamos sftp para tranferir el archivo .jar de nuestro proyecto.

Este es el comando que tenemos que ejecutar en la terminal:
```
sftp -i "Credentials.pem" ec2-user@ec2-18-209-23-92.compute-1.amazonaws.com
```

Buscamos de manera local el archivo para tranferirlo y con el siguiente comando lo pasamos a la maquina virtual

```
put <nombre del archivo>.jar
```
![Cosola de salida](/images/30.png)

7. Arrancamos el servicion del archivo jar

Para configurar java 1.8 utiliza estos comandos:
```
sudo yum install java-1.8.0
sudo /usr/sbin/alternatives --config java
```

Este es el comando que tenemos que ejecutar en la terminal:
```
java -jar <nombre del archivo>.jar
```
![Cosola de salida](/images/31.png)

## 3. Enlazar el formulario a una base de datos relacional o no-relacional, para almacenar y traer los datos almacenados. Use servicios de base de datos de AWS

Para este apartado usaremos Amazon DynamoDB para el almacenamiento de los datos.
![Cosola de salida](/images/36.png)

![Cosola de salida](/images/37.png)

![Cosola de salida](/images/38.png)


## 4. Configurar un VPC para gestionar la seguridad y los permisos de acceso a sus servidores.

Nos dirigimos a "Security Groups" en el dashboard.
Seleccionamos el primer item de la lista
En la parte de inferior nos dirigimos a la pestaña Inbound.
![Cosola de salida](/images/35.png)
Le damos en el boton de edit, y nos aparecera la siguiente ventana:
<br/>
Le damos en el boton de add rule y escojemos la opcion de Custom TCP, en el port range ponemos el puerto por el que esta corriendo nuestra aplicacion.
Guardamos los cambios.

![Cosola de salida](/images/33.png)

A continucion visitamos puestra pagina para comprobar que la regla fue correctamente configurada.
![Cosola de salida](/images/34.png)


![Cosola de salida](/images/32.png)