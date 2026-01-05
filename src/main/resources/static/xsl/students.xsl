<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
      xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

<xsl:template match="/">
<html>
<head>
    <title>Students</title>
</head>
<body>
<h2>Students</h2>

<table border="1" cellpadding="5">
<tr>
  <th>ID</th>
  <th>Name</th>
  <th>Birth date</th>
  <th>Group</th>
  <th>Courses</th>
</tr>

<xsl:for-each select="students/student">
<tr>
  <td><xsl:value-of select="id"/></td>
  <td><xsl:value-of select="name"/></td>
  <td><xsl:value-of select="birthDate"/></td>
  <td><xsl:value-of select="groupName"/></td>
  <td>
    <xsl:for-each select="courses/course">
        <xsl:value-of select="courseTitle"/>
        <xsl:text> (mark: </xsl:text>
        <xsl:value-of select="mark"/>
        <xsl:text>)</xsl:text>
        <br/>
    </xsl:for-each>
  </td>
</tr>
</xsl:for-each>

</table>
</body>
</html>
</xsl:template>

</xsl:stylesheet>
