using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using FireAlarmMonitoringSystem.Web.Data.Entities;
using FireAlarmMonitoringSystem.Web.Data.Persistence;
using Microsoft.Extensions.Configuration;

namespace FireAlarmMonitoringSystem.Web.API.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class UserController : ControllerBase
    {
        private readonly AppDbContext _context;
        private readonly IUserPersistenceService _userPerService;

        public UserController(AppDbContext context, IUserPersistenceService userPerService)
        {
            _context = context;
            _userPerService = userPerService;
        }

        [HttpPost("SignIn")]
        public async Task<UserModel> SignIn(UserModel curUser)
        {
            UserModel dbUser = await _userPerService.SignIn(curUser);
            return dbUser;
            //Authentication authentication = new Authentication(_configuration);
            //return authentication.GetToken(loginUser);
        }

        [HttpPost("AddUser")]
        public async Task<ApiResult> AddUser(UserModel user)
        {
            return await _userPerService.AddUser(user);
        }
    }
}