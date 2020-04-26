using Microsoft.EntityFrameworkCore.Migrations;

namespace FireAlarmMonitoringSystem.Web.Data.Migrations
{
    public partial class FirstMigration : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.CreateTable(
                name: "SensorDetails",
                columns: table => new
                {
                    sensorId = table.Column<int>(nullable: false)
                        .Annotation("SqlServer:Identity", "1, 1"),
                    sensorName = table.Column<string>(nullable: true),
                    floorNo = table.Column<string>(nullable: true),
                    roomNo = table.Column<string>(nullable: true),
                    smokeLevel = table.Column<int>(nullable: false),
                    coLevel = table.Column<int>(nullable: false),
                    sensorStatus = table.Column<string>(nullable: true)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_SensorDetails", x => x.sensorId);
                });

            migrationBuilder.CreateTable(
                name: "Users",
                columns: table => new
                {
                    userId = table.Column<int>(nullable: false)
                        .Annotation("SqlServer:Identity", "1, 1"),
                    userEmail = table.Column<string>(nullable: true),
                    userName = table.Column<string>(nullable: true),
                    userPassword = table.Column<string>(nullable: true),
                    userRole = table.Column<string>(nullable: true)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Users", x => x.userId);
                });
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropTable(
                name: "SensorDetails");

            migrationBuilder.DropTable(
                name: "Users");
        }
    }
}
