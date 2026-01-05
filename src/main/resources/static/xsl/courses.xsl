<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
      xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

<xsl:template match="/courses">
<html>
<head>
    <title>Courses</title>
</head>
<body>

<h2>Courses</h2>

<table border="1" cellpadding="5">
    <tr>
        <th>ID</th>
        <th>Title</th>
        <th>Professor</th>
        <th>Details</th>
    </tr>

    <xsl:for-each select="course">
        <tr>
            <td><xsl:value-of select="id"/></td>
            <td><xsl:value-of select="title"/></td>
            <td><xsl:value-of select="professor"/></td>
            <td>
                <a>
                    <xsl:attribute name="href">
                        /api/courses/<xsl:value-of select="id"/>
                    </xsl:attribute>
                    view
                </a>
            </td>
        </tr>
    </xsl:for-each>
</table>

<p>
    <a href="/api/students">Go to students</a>
</p>

</body>
</html>
</xsl:template>

</xsl:stylesheet>
