package com.trs.servlet;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DownloadServlet extends HttpServlet
{
  /*public static void pack( final String sourceDirPath, final String zipFilePath ) throws IOException
  {
    final Path p = Files.createFile( Paths.get( zipFilePath ) );
    final Path pp = Paths.get( sourceDirPath );
    try ( ZipOutputStream zs = new ZipOutputStream( Files.newOutputStream( p ) );
        Stream<Path> paths = Files.walk( pp ) )
    {
      paths.filter( path -> !Files.isDirectory( path ) ).forEach( path ->
      {
        final ZipEntry zipEntry = new ZipEntry( pp.relativize( path ).toString() );
        try
        {
          zs.putNextEntry( zipEntry );
          Files.copy( path, zs );
          zs.closeEntry();
        }
        catch ( final IOException e )
        {
          System.err.println( e );
        }
      } );
    }
  }
  */
  @Override
  public void doGet( final HttpServletRequest request, final HttpServletResponse response ) throws ServletException,
                                                                                            IOException
  {

    /*   final String paramName = "filename";
    final String paramValue = request.getParameter( paramName );
    final String paramName1 = "filepath";
    final String paramValue1 = request.getParameter( paramName1 );
    System.out.println( "Param==" + paramValue );
    System.out.println( "Param==" + paramValue1 );*/

    final String filename = request.getParameter( "filename" );
    final String filepath = request.getParameter( "filepath" );
    response.setContentType( "text/html" );
    final PrintWriter out = response.getWriter();

    response.setContentType( "APPLICATION/DOWNLOAD" );
    response.setHeader( "Content-Disposition", "attachment; filename=\"" + filename + "\"" );
    // pack( sourcepath, destpath );

    final FileInputStream fileInputStream = new FileInputStream( filepath + "/" + filename );

    int i;
    while ( ( i = fileInputStream.read() ) != -1 )
    {
      out.write( i );
    }
    fileInputStream.close();
    out.close();
  }

}
